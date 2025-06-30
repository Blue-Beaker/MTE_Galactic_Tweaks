package io.bluebeaker.mtegctweaks.mixin;

import micdoodle8.mods.galacticraft.core.tile.TileEntityInventory;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityGasLiquefier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig.fixInventorySize;

@Mixin(value = TileEntityGasLiquefier.class,remap = false)
public abstract class MixinTileEntityGasLiquefier extends TileEntityInventory {
    public MixinTileEntityGasLiquefier(String tileName) {
        super(tileName);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void initGasLiquefier(CallbackInfo ci){
        if(fixInventorySize){
            this.inventory = NonNullList.withSize(4, ItemStack.EMPTY);
        };
    }
}
