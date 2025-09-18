package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaks;
import micdoodle8.mods.galacticraft.core.tile.TileEntityInventory;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityGasLiquefier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Objects;

import static io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig.fixInventorySize;

@Mixin(value = TileEntityGasLiquefier.class,remap = false)
public abstract class MixinTileEntityGasLiquefier extends TileEntityInventory {
    public MixinTileEntityGasLiquefier(String tileName) {
        super(tileName);
    }
//    @Inject(method = "<init>",at = @At("RETURN"))
//    public void initGasLiquefier(CallbackInfo ci){
//        if(fixInventorySize){
//            this.inventory = NonNullList.withSize(4, ItemStack.EMPTY);
//        };
//    }

    @Inject(method = "Lmicdoodle8/mods/galacticraft/planets/mars/tile/TileEntityGasLiquefier;getSlotsForFace(Lnet/minecraft/util/EnumFacing;)[I",at = @At("RETURN"),remap = true, cancellable = true)
    public void initGasLiquefier(EnumFacing side, CallbackInfoReturnable<int[]> cir){
        if(fixInventorySize){
            int[] returnValue = cir.getReturnValue();
            int[] modifiedValue = Arrays.stream(returnValue).filter((num) -> num < 3).toArray();
            cir.setReturnValue(modifiedValue);
        };
    }
}
