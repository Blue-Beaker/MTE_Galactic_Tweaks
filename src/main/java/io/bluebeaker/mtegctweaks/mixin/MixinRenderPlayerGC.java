package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.core.client.render.entities.RenderPlayerGC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayerGC.class,remap = false)
public abstract class MixinRenderPlayerGC {
    @Inject(method = "addGCLayers()V",at = @At("HEAD"),cancellable = true)
    public void hideGCEquipments(CallbackInfo ci){
        if(MTEGalacticTweaksConfig.hideEquipments) ci.cancel();
    }
}
