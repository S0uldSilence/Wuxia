package com.github.s0uldsilence.wuxia.item;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.item.custom.ManaCrystalItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Wuxia.MODID);

    public static final RegistryObject<Item> GREEN_JADE = ITEMS.register("green_jade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_CRYSTAL = ITEMS.register("mana_crystal",
            () -> new ManaCrystalItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
