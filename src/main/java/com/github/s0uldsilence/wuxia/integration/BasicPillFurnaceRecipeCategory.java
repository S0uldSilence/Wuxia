package com.github.s0uldsilence.wuxia.integration;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.recipe.BasicPillFurnaceRecipe;
import com.github.s0uldsilence.wuxia.setup.Registration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class BasicPillFurnaceRecipeCategory implements IRecipeCategory<BasicPillFurnaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Wuxia.MODID, "basic_pill_furnaceing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Wuxia.MODID, "textures/gui/basic_pill_furnace_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BasicPillFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Registration.BASIC_PILL_FURNACE.get()));
    }

    @Override
    public RecipeType<BasicPillFurnaceRecipe> getRecipeType() {
        return JEIWuxiaModPlugin.PILL_FURNACE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Basic Pill Furnace");
        //return Component.translatable("recipe_category.wuxia.basic_pill_furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BasicPillFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
