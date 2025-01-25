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

    @Comment({"Change what tier of EU power can be inserted to GC blocks without exploding.",
            " GC default is 3 (512EU)."})
    @LangKey("config.mtegctweaks.EUTierForGC.name")
    public static int EUTierForGC = 5;

    @Comment({"When receiving energy from IC2, use IC2 voltage to boost machines.","Aluminum wires are capped at tier 2."})
    @LangKey("config.mtegctweaks.ic2EnergyTierFix.name")
    public static boolean ic2EnergyTierFix = true;
    @Comment({"Same as above, but for IC2 batteries."})
    @LangKey("config.mtegctweaks.ic2EnergyTierFixBattery.name")
    public static boolean ic2EnergyTierFixBattery = true;
}