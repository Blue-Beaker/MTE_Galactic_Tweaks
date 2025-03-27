package io.bluebeaker.mtegctweaks.mixin;

import micdoodle8.mods.galacticraft.planets.mars.client.gui.GuiSlimeling;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntitySlimeling;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiSlimeling.class,remap = false)
public abstract class MixinGuiSlimeling {
    @Unique
    private boolean mte_gc_tweaks$canRenameSLimeling = false;

    @Final
    @Shadow
    private EntitySlimeling slimeling;

    @Inject(method = "initGui()V", at = @At("RETURN"), remap = true)
    public void initTextbox(CallbackInfo ci){
        if(this.slimeling.isOwner(Minecraft.getMinecraft().player)){
            ((GuiSlimeling)(Object)this).nameField.setFocused(false);
            ((GuiSlimeling)(Object)this).nameField.setCanLoseFocus(true);
        }
    }


    @Inject(method = "keyTyped(CI)V", at = @At("RETURN"), remap = true)
    protected void initTextbox(char typedChar, int keyCode, CallbackInfo ci){
        if(!((GuiSlimeling)(Object)this).nameField.isFocused() && (Minecraft.getMinecraft().gameSettings.keyBindInventory.isActiveAndMatches(keyCode))){
            Minecraft.getMinecraft().player.closeScreen();
        }
    }
}
