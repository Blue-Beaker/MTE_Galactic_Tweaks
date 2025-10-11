package io.bluebeaker.mtegctweaks.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockMachineTiered;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArcFurnaceCategory extends GenericRecipeCategory<ArcFurnaceCategory.Wrapper> {
    public static final String UID = "category.mtegctweaks.arc_furnace";
    public static final ResourceLocation GUI_PATH = new ResourceLocation("galacticraftcore","textures/gui/electric_arc_furnace.png");
    protected final IDrawableStatic bgArrow;
    protected final IDrawableAnimated arrow;

    public static ItemStack catalyst = ItemStack.EMPTY;

    public ArcFurnaceCategory(IGuiHelper guiHelper) {
        super(guiHelper,100,32);
        this.bgArrow = guiHelper.createDrawable(GUI_PATH, 78, 24, 23, 15);
        this.arrow = guiHelper.drawableBuilder(GUI_PATH, 176, 0, 23, 15).buildAnimated(20, IDrawableAnimated.StartDirection.LEFT, false);
        catalyst=new ItemStack(GCBlocks.machineTiered,1,BlockMachineTiered.EnumTieredMachineType.ARC_FURNACE.getMetadata());
        this.icon = guiHelper.createDrawableIngredient(catalyst);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Wrapper wrapper, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStackGroup = recipeLayout.getItemStacks();
        this.addItemSlot(guiItemStackGroup,0,8,(GUI_HEIGHT-18)/2);
        guiItemStackGroup.set(0,wrapper.input);
        this.addItemSlot(guiItemStackGroup,1,GUI_WIDTH-24,(GUI_HEIGHT-18)/2);
        guiItemStackGroup.set(1,wrapper.output);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        this.bgArrow.draw(minecraft, (GUI_WIDTH-23)/2, (GUI_HEIGHT-15)/2);
        this.arrow.draw(minecraft, (GUI_WIDTH-23)/2, (GUI_HEIGHT-15)/2);
    }

    @Override
    public String getTitle() {
        return catalyst.getDisplayName();
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getModName() {
        return "galacticraftcore";
    }

    public static List<Wrapper> getRecipes(IJeiHelpers jeiHelpers){
        List<Wrapper> recipes = new ArrayList<>();
        Map<ItemStack, ItemStack> smeltingList = FurnaceRecipes.instance().getSmeltingList();
        for (Map.Entry<ItemStack, ItemStack> entry : smeltingList.entrySet()) {
            String nameSmelted = entry.getKey().getTranslationKey().toLowerCase();
            ItemStack resultItemStack = entry.getValue().copy();
            resultItemStack.setCount(resultItemStack.getCount()*2);
            if ((resultItemStack.getTranslationKey().toLowerCase().contains("ingot") || resultItemStack.getItem() == Items.QUARTZ)
                    && (nameSmelted.contains("ore") || nameSmelted.contains("raw") || nameSmelted.contains("moon") || nameSmelted.contains("mars") || nameSmelted.contains("shard"))){
                recipes.add(new Wrapper(jeiHelpers,entry.getKey(),resultItemStack));
            }
        }

        return recipes;
    }

    public static class Wrapper implements IRecipeWrapper {
        public final ItemStack input;
        public final ItemStack output;

        public Wrapper(IJeiHelpers jeiHelpers, ItemStack input, ItemStack output) {
            this.input=input;
            this.output=output;
        }

        @Override
        public void getIngredients(IIngredients iIngredients) {
            iIngredients.setInput(VanillaTypes.ITEM, input);
            iIngredients.setOutput(VanillaTypes.ITEM, output);
        }
    }
}
