package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityGasLiquefier;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityGasLiquefier.class,remap = false)
public class MixinGasLiquefier {
    @Redirect(method = "getTankInfo(Lnet/minecraft/util/EnumFacing;)[Lnet/minecraftforge/fluids/FluidTankInfo;",at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/planets/mars/tile/TileEntityGasLiquefier;getElectricInputDirection()Lnet/minecraft/util/EnumFacing;"))
    public EnumFacing fixTankInfo(TileEntityGasLiquefier instance){
        return mte_gc_tweaks$fixFacing(instance);
    }

    @Redirect(method = "canDrain(Lnet/minecraft/util/EnumFacing;Lnet/minecraftforge/fluids/Fluid;)Z",at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/planets/mars/tile/TileEntityGasLiquefier;getElectricInputDirection()Lnet/minecraft/util/EnumFacing;"))
    public EnumFacing fixCanDrain(TileEntityGasLiquefier instance){
        return mte_gc_tweaks$fixFacing(instance);
    }

    @Redirect(method = "drain(Lnet/minecraft/util/EnumFacing;Lnet/minecraftforge/fluids/FluidStack;Z)Lnet/minecraftforge/fluids/FluidStack;",at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/planets/mars/tile/TileEntityGasLiquefier;getElectricInputDirection()Lnet/minecraft/util/EnumFacing;"))
    public EnumFacing drain(TileEntityGasLiquefier instance){
        return mte_gc_tweaks$fixFacing(instance);
    }

    @Redirect(method = "drain(Lnet/minecraft/util/EnumFacing;IZ)Lnet/minecraftforge/fluids/FluidStack;",at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/planets/mars/tile/TileEntityGasLiquefier;getElectricInputDirection()Lnet/minecraft/util/EnumFacing;"))
    public EnumFacing drain2(TileEntityGasLiquefier instance){
        return mte_gc_tweaks$fixFacing(instance);
    }

    @Unique
    private static EnumFacing mte_gc_tweaks$fixFacing(TileEntityGasLiquefier instance) {
        if(MTEGalacticTweaksConfig.gasLiquefierFluidFacingFix)
            return instance.getGasInputDirection();
        return instance.getElectricInputDirection();
    }
}
