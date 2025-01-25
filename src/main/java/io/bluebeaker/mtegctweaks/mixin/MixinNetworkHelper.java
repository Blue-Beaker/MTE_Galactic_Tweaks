package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.AdapterDummyNetwork;
import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.core.fluid.FluidNetwork;
import micdoodle8.mods.galacticraft.core.fluid.NetworkHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NetworkHelper.class,remap = false)
public abstract class MixinNetworkHelper {
    @Inject(method = "getFluidNetworkFromTile(Lnet/minecraft/tileentity/TileEntity;Lnet/minecraft/util/EnumFacing;)Lmicdoodle8/mods/galacticraft/core/fluid/FluidNetwork;",at = @At("TAIL"),cancellable = true)
    private static void addAdaptationNetwork(TileEntity tileEntity, EnumFacing approachDirection, CallbackInfoReturnable<FluidNetwork> cir){
        if(!MTEGalacticTweaksConfig.universalFluidEjection || cir.getReturnValue()!=null) return;

        if(tileEntity!=null && tileEntity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,approachDirection.getOpposite())){
            cir.setReturnValue(new AdapterDummyNetwork(tileEntity,approachDirection.getOpposite()));
        }
    }
}
