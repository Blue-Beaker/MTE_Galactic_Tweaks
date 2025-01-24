package io.bluebeaker.mtegctweaks;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MTEGalacticTweaks.MODID,type = Type.INSTANCE,category = "general")
public class MTEGalacticTweaksConfig {
    @Comment("Example")
    @LangKey("config.mtegctweaks.example.name")
    public static boolean example = true;
}