package io.bluebeaker.mtegctweaks.mixin;

import ic2.api.energy.tile.IEnergySource;
import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalConductor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileBaseUniversalConductor.class,remap = false)
public class MixinTileConductor {
    @Shadow
    float IC2surplusJoules;

    @Inject(method = "injectEnergy",at = @At(value = "HEAD"),cancellable = true)
    public void injectEnergy(EnumFacing directionFrom, double amount, double voltage, CallbackInfoReturnable<Double> cir){
        if(!MTEGalacticTweaksConfig.ic2EnergyTierFix) return;
        TileBaseUniversalConductor conductor = (TileBaseUniversalConductor)(Object)this;
        TileEntity tile = new BlockVec3(conductor).getTileEntityOnSide(conductor.getWorld(), directionFrom);
        // The difference from GC's original code. IC2 now passes voltage in 1,2,3,4,5 instead of EU/packet values
        int tier = Math.min((int)voltage,2);
        if (tile instanceof IEnergySource && ((IEnergySource) tile).getOfferedEnergy() >= 128)
        {
            tier = 2;
        }
        float convertedEnergy = (float) amount * EnergyConfigHandler.IC2_RATIO;
        float surplus = conductor.getNetwork().produce(convertedEnergy, true, tier, conductor, tile);

        if (surplus >= 0.001F)
        {
            this.IC2surplusJoules = surplus;
        }
        else
        {
            this.IC2surplusJoules = 0F;
        }

        cir.setReturnValue(0D);
    }

    @Inject(method = "getDemandedEnergy",at = @At("RETURN"),cancellable = true)
    public void getDemandedEnergy(CallbackInfoReturnable<Double> cir){
        if(!MTEGalacticTweaksConfig.ic2EnergyTierFix) return;
        Double returnValue = cir.getReturnValue();
        if(returnValue>0){
            // Try to let IC2 output in higher voltage. We have surplus for excess energy
            cir.setReturnValue(Math.max(33D,returnValue));
        }
    }

    @Inject(method = "getSinkTier",at = @At(value = "HEAD"),cancellable = true)
    public void getSinkTier(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(MTEGalacticTweaksConfig.EUTierForGC);
    }
}
