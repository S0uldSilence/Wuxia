package com.github.s0uldsilence.wuxia.recipe;

import com.github.s0uldsilence.wuxia.Wuxia;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Wuxia.MODID);

    public static final RegistryObject<RecipeSerializer<BasicPillFurnaceRecipe>> GEM_INFUSING_SERIALIZER =
            SERIALIZERS.register("basic_pill_furnaceing", () -> BasicPillFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
