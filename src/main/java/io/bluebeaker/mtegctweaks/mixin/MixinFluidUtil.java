package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.core.items.ItemBucketGC;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.minecraftforge.fluids.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FluidUtil.class,remap = false)
public class MixinFluidUtil {
    @Redirect(method = "tryFillContainer(Lnet/minecraftforge/fluids/FluidTank;Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraft/util/NonNullList;ILnet/minecraft/item/Item;)V",at = @At(value = "INVOKE", target = "Lmicdoodle8/mods/galacticraft/core/items/ItemBucketGC;getBucketForFluid(Lnet/minecraftforge/fluids/Fluid;)Lmicdoodle8/mods/galacticraft/core/items/ItemBucketGC;"))
    private static ItemBucketGC getBucketForFluid(Fluid fluid){
        if(MTEGalacticTweaksConfig.forgeBuckets)
            return null;
        else
            return ItemBucketGC.getBucketForFluid(fluid);
    }
}
