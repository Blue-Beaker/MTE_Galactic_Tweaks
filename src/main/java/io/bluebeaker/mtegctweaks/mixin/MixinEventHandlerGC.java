package io.bluebeaker.mtegctweaks.mixin;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EventHandlerGC.class,remap = false)
public class MixinEventHandlerGC {
    @Inject(method = "onBucketFill",at = @At("HEAD"),cancellable = true)
    public void notGCBucket(FillBucketEvent event, CallbackInfo ci){
        if(MTEGalacticTweaksConfig.forgeBuckets)
            ci.cancel();
    }
}
