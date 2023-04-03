package com.github.s0uldsilence.wuxia;

import com.github.s0uldsilence.wuxia.block.ModBlocks;
import com.github.s0uldsilence.wuxia.block.entity.ModBlockEntities;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.datagen.DataGenerators;
import com.github.s0uldsilence.wuxia.datagen.WuxiaTomeProvider;
import com.github.s0uldsilence.wuxia.formation.Formations;
import com.github.s0uldsilence.wuxia.item.ModCreativeModeTabs;
import com.github.s0uldsilence.wuxia.item.ModItems;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import com.github.s0uldsilence.wuxia.recipe.ModRecipes;
import com.github.s0uldsilence.wuxia.screen.TileEntities.BasicPillFurnaceScreen;
import com.github.s0uldsilence.wuxia.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Wuxia.MODID)
public class Wuxia {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "wuxia";
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();
    public Wuxia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        CultivationMethods.registerCultivationMethods();
        Formations.registerFormations();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GREEN_JADE);
        }

        if(event.getTab() == ModCreativeModeTabs.WUXIA_TAB) {
            event.accept(ModItems.GREEN_JADE);
            event.accept(ModBlocks.GREEN_JADE_BLOCK);
            event.accept(ModBlocks.GREEN_JADE_ORE);
            event.accept(ModBlocks.DEEPSLATE_GREEN_JADE_ORE);
            event.accept(ModBlocks.BASIC_PILL_FURNACE);

            event.accept(ModBlocks.MANA_CRYSTAL_ORE);
            event.accept(ModItems.MANA_CRYSTAL);
            event.accept(ModItems.CULTIVATION_CHECKER);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.BASIC_PILL_FURNACE_MENU.get(), BasicPillFurnaceScreen::new);
        }
    }


}
