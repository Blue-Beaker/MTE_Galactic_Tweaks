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

    @Comment({"Change the voltage of EU power can be inserted to GC blocks without exploding.",
            "GC default is 3 (512EU)."})
    @LangKey("config.mtegctweaks.EUTierForGC.name")
    public static int EUTierForGC = 5;

    @Comment({"When importing power from IC2, IC2 voltage >=2V can now boost GC machines.",
            "GC wires are capped at 2V. Use IC2 cables for higher tiers and better speed."})
    @LangKey("config.mtegctweaks.ic2EnergyTierFix.name")
    public static boolean ic2EnergyTierFix = true;

    @Comment({"Same as above, but for IC2 batteries."})
    @LangKey("config.mtegctweaks.ic2EnergyTierFixBattery.name")
    public static boolean ic2EnergyTierFixBattery = true;

    @Comment({"Fix the side to pull fluid from the liquefier."})
    @LangKey("config.mtegctweaks.gasLiquefierFluidFacingFix.name")
    public static boolean gasLiquefierFluidFacingFix = true;

    @Comment({"In slimeling GUI, do not autofocus the textbox."})
    @LangKey("config.mtegctweaks.slimelingGuiTweaks.name")
    public static boolean slimelingGuiTweaks = true;

    @Comment({"Disable GC's own fluid Buckets, replacing them with forge buckets."})
    @LangKey("config.mtegctweaks.forgeBuckets.name")
    public static boolean forgeBuckets = false;

    @Comment({"Fix Gas Liquefier inventory size, which causes IndexOutOfBounds crash when trying to extract item from it."})
    @LangKey("config.mtegctweaks.fixInventorySize.name")
    public static boolean fixInventorySize = true;

    @Config.RequiresMcRestart
    @Comment({"Disable rendering of GalactiCraft equipments. May increase FPS."})
    @LangKey("config.mtegctweaks.hideEquipments.name")
    public static boolean hideEquipments = false;
}