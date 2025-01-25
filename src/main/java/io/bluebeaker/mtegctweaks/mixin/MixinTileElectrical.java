package io.bluebeaker.mtegctweaks.mixin;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.ISpecialElectricItem;
import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = TileBaseUniversalElectrical.class,remap = false)
public abstract class MixinTileElectrical {
    @Inject(method = "discharge(Lnet/minecraft/item/ItemStack;)V",at = @At(value = "INVOKE",target = "Lmicdoodle8/mods/galacticraft/core/energy/tile/EnergyStorage;receiveEnergyGC(F)F",shift = At.Shift.AFTER))
    public void acceptIC2VoltageFromItem(ItemStack itemStack, CallbackInfo ci){
        if(!MTEGalacticTweaksConfig.ic2EnergyTierFixBattery) return;
        Item item = itemStack.getItem();
        if(!(item instanceof IElectricItem)) return;
        IElectricItem electricItem = (IElectricItem) item;
        if(!electricItem.canProvideEnergy(itemStack))
            return;
        if(item instanceof ISpecialElectricItem){
            ISpecialElectricItem specialItem = (ISpecialElectricItem)item;
            if(specialItem.getManager(itemStack).getCharge(itemStack)<=0)
                return;
        }else {
            if(ElectricItem.manager.getCharge(itemStack)<=0)
                return;
        }
        ((TileBaseUniversalElectrical)(Object)this)
                .poweredByTierGC=Math.min(electricItem.getTier(itemStack),5);
    }

    @Inject(method = "getDemandedEnergy",at = @At("RETURN"),cancellable = true)
    public void getDemandedEnergy(CallbackInfoReturnable<Double> cir){
        if(!MTEGalacticTweaksConfig.ic2EnergyTierFix) return;
        Double returnValue = cir.getReturnValue();
        if(returnValue>0){
            // Try to let IC2 output in higher voltage. We have surplus for excess energy
            cir.setReturnValue(Math.max(2049D,returnValue));
        }
    }

    @Shadow
    float IC2surplusInGJ;
    @Inject(method = "injectEnergy", at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/core/energy/tile/TileBaseUniversalElectrical;receiveElectricity(Lnet/minecraft/util/EnumFacing;FIZ)F"),cancellable = true,locals = LocalCapture.CAPTURE_FAILHARD)
    public void injectEnergy(EnumFacing direction, double amount, double voltage, CallbackInfoReturnable<Double> cir, float convertedEnergy)
    {
        if(!MTEGalacticTweaksConfig.ic2EnergyTierFix) return;
        TileBaseUniversalElectrical original = (TileBaseUniversalElectrical)(Object)this;
//        float convertedEnergy = (float) amount * EnergyConfigHandler.IC2_RATIO;
        // The difference from GC's original code. IC2 now passes voltage in 1,2,3,4,5 instead of EU/packet values
        int tierFromIC2 = Math.min((int) voltage, 5);
        float receive = original.receiveElectricity(direction == null ? null : direction.getOpposite(), convertedEnergy, tierFromIC2, true);

        if (convertedEnergy > receive)
        {
            this.IC2surplusInGJ = convertedEnergy - receive;
        } else
        {
            this.IC2surplusInGJ = 0F;
        }
        cir.setReturnValue(0D);
    }

    @Inject(method = "getSinkTier",at = @At(value = "HEAD"),cancellable = true)
    public void getSinkTier(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(MTEGalacticTweaksConfig.EUTierForGC);
    }
}
