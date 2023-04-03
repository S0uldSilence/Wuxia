package com.github.s0uldsilence.wuxia.screen;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.screen.TileEntities.BasicPillFurnaceMenu;
import com.github.s0uldsilence.wuxia.screen.TileEntities.FormationCoreMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Wuxia.MODID);

    public static final RegistryObject<MenuType<BasicPillFurnaceMenu>> BASIC_PILL_FURNACE_MENU =
            registerMenuType(BasicPillFurnaceMenu::new, "basic_pill_furnace_menu");
    public static final RegistryObject<MenuType<FormationCoreMenu>> FORMATION_CORE_MENU =
            registerMenuType(FormationCoreMenu::new, "formation_core_menu");
    

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
