package com.github.s0uldsilence.wuxia.integration;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.recipe.BasicPillFurnaceRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIWuxiaModPlugin implements IModPlugin {
    public static RecipeType<BasicPillFurnaceRecipe> PILL_FURNACE_TYPE =
            new RecipeType<>(BasicPillFurnaceRecipeCategory.UID, BasicPillFurnaceRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Wuxia.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                BasicPillFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<BasicPillFurnaceRecipe> recipesInfusing = rm.getAllRecipesFor(BasicPillFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(PILL_FURNACE_TYPE, recipesInfusing);
    }
}
