package io.bluebeaker.mtegctweaks.jei;

import io.bluebeaker.mtegctweaks.MTEGalacticTweaks;
import io.bluebeaker.mtegctweaks.MTEGalacticTweaksConfig;
import io.bluebeaker.mtegctweaks.utils.LogTimer;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;


@JEIPlugin
public class GCTweaksPlugin implements IModPlugin {

  private static IJeiRuntime jeiRuntime = null;
  public static IModRegistry modRegistry;

  @Override
  public void registerCategories(IRecipeCategoryRegistration registry) {
    IJeiHelpers jeiHelpers = registry.getJeiHelpers();
    IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
    if(MTEGalacticTweaksConfig.arcFurnaceRecipes){
      registry.addRecipeCategories(new ArcFurnaceCategory(guiHelper));
    }
  }

  @Override
  public void register(IModRegistry registry) {
    modRegistry=registry;
    IJeiHelpers jeiHelpers = registry.getJeiHelpers();
    LogTimer timer = new LogTimer();
    MTEGalacticTweaks.getLogger().info("Started loading recipes...");
    if(MTEGalacticTweaksConfig.arcFurnaceRecipes){
      registry.addRecipes(ArcFurnaceCategory.getRecipes(jeiHelpers),ArcFurnaceCategory.UID);
      registry.addRecipeCatalyst(ArcFurnaceCategory.catalyst,ArcFurnaceCategory.UID);
      MTEGalacticTweaks.getLogger().info("Arc furnace recipes loaded in {}ms",timer.stagedTime());
    }
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntimeIn) {
    GCTweaksPlugin.jeiRuntime = jeiRuntimeIn;
  }
}