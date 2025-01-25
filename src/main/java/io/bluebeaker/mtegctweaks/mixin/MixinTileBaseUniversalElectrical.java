package io.bluebeaker.mtegctweaks.mixin;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.ISpecialElectricItem;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileBaseUniversalElectrical.class,remap = false)
public abstract class MixinTileBaseUniversalElectrical {
    @Inject(method = "discharge(Lnet/minecraft/item/ItemStack;)V",at = @At(value = "INVOKE",target = "Lmicdoodle8/mods/galacticraft/core/energy/tile/EnergyStorage;receiveEnergyGC(F)F"))
    public void acceptIC2VoltageFromItem(ItemStack itemStack, CallbackInfo ci){
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
                .poweredByTierGC=Math.min(electricItem.getTier(itemStack),6);
    }
    @ModifyVariable(method = "injectEnergy", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    public double acceptIC2VoltageFromBlock(double tier){
        return Math.pow(2,tier)*16;
    }
}
