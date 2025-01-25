package io.bluebeaker.mtegctweaks;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MTEGalacticTweaks.MODID,type = Type.INSTANCE,category = "general")
public class MTEGalacticTweaksConfig {
    @Comment("When enabled, GC machines will eject fluids in its output to all forge-capable blocks, like tanks and pipes from other mods.")
    @LangKey("config.mtegctweaks.universalFluidEjection.name")
    public static boolean universalFluidEjection = true;
}