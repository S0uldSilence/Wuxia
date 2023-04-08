package com.github.s0uldsilence.wuxia.setup;
import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.custom.BasicPillFurnaceBlock;
import com.github.s0uldsilence.wuxia.block.custom.FormationCoreBlock;
import com.github.s0uldsilence.wuxia.block.entity.BasicPillFurnaceBE;
import com.github.s0uldsilence.wuxia.block.entity.FormationCoreBE;
import com.github.s0uldsilence.wuxia.block.essence.EssenceJar;
import com.github.s0uldsilence.wuxia.block.essence.EssenceJarBE;
import com.github.s0uldsilence.wuxia.item.custom.CultivationCheckerItem;
import com.github.s0uldsilence.wuxia.item.custom.CultivationMethodItem;
import com.github.s0uldsilence.wuxia.item.custom.ManaCrystalItem;
import com.github.s0uldsilence.wuxia.screen.TileEntities.BasicPillFurnaceMenu;
import com.github.s0uldsilence.wuxia.screen.TileEntities.FormationCoreMenu;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.s0uldsilence.wuxia.Wuxia.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
    private static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);
    private static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(Registries.CHUNK_GENERATOR, MODID);
    private static final DeferredRegister<Codec<? extends BiomeSource>> BIOME_SOURCES = DeferredRegister.create(Registries.BIOME_SOURCE, MODID);
    private static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        STRUCTURES.register(bus);
        BIOME_MODIFIERS.register(bus);
        CHUNK_GENERATORS.register(bus);
        BIOME_SOURCES.register(bus);
        PLACEMENT_MODIFIERS.register(bus);
    }
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties();

    public static final RegistryObject<Block> MANA_CRYSTAL_ORE = BLOCKS.register("mana_crystal_ore", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MANA_ORE_ITEM = fromBlock(MANA_CRYSTAL_ORE);
    public static final RegistryObject<Block> DEEPSLATE_GREEN_JADE_ORE = BLOCKS.register("deepslate_green_jade_ore", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> DEEPSLATE_GREEN_JADE_ORE_ITEM = fromBlock(DEEPSLATE_GREEN_JADE_ORE);

    public static final RegistryObject<Block> JADE_ORE = BLOCKS.register("jade_ore", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> JADE_ORE_ITEM = fromBlock(JADE_ORE);


    public static final RegistryObject<Item> MANA_CRYSTAL = ITEMS.register("mana_crystal", () -> new ManaCrystalItem(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GREEN_JADE = ITEMS.register("green_jade", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Block> GREEN_JADE_BLOCK = BLOCKS.register("green_jade_block", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GREEN_JADE_BLOCK_ITEM = fromBlock(GREEN_JADE_BLOCK);
    public static final RegistryObject<Block> GREEN_JADE_ORE = BLOCKS.register("green_jade_ore", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> GREEN_JADE_ORE_ITEM = fromBlock(GREEN_JADE_ORE);
    public static final RegistryObject<Item> CULTIVATION_METHOD = ITEMS.register("cultivation_method", () -> new CultivationMethodItem(ITEM_PROPERTIES));
    public static final RegistryObject<Item> CULTIVATION_CHECKER = ITEMS.register("cultivation_checker", () -> new CultivationCheckerItem(ITEM_PROPERTIES));
    public static final RegistryObject<Block> AIR_ELEMENT_RUNE = BLOCKS.register("air_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> AIR_ELEMENT_RUNE_ITEM = fromBlock(AIR_ELEMENT_RUNE);
    public static final RegistryObject<Block> EARTH_ELEMENT_RUNE = BLOCKS.register("earth_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> EARTH_ELEMENT_RUNE_ITEM = fromBlock(EARTH_ELEMENT_RUNE);
    public static final RegistryObject<Block> FIRE_ELEMENT_RUNE = BLOCKS.register("fire_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> FIRE_ELEMENT_RUNE_ITEM = fromBlock(FIRE_ELEMENT_RUNE);
    public static final RegistryObject<Block> WATER_ELEMENT_RUNE = BLOCKS.register("water_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> WATER_ELEMENT_RUNE_ITEM = fromBlock(WATER_ELEMENT_RUNE);
    public static final RegistryObject<Block> LIFE_ELEMENT_RUNE = BLOCKS.register("life_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> LIFE_ELEMENT_RUNE_ITEM = fromBlock(LIFE_ELEMENT_RUNE);
    public static final RegistryObject<Block> DEATH_ELEMENT_RUNE = BLOCKS.register("death_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> DEATH_ELEMENT_RUNE_ITEM = fromBlock(DEATH_ELEMENT_RUNE);
    public static final RegistryObject<Block> SPACE_ELEMENT_RUNE = BLOCKS.register("space_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> SPACE_ELEMENT_RUNE_ITEM = fromBlock(SPACE_ELEMENT_RUNE);
    public static final RegistryObject<Block> TIME_ELEMENT_RUNE = BLOCKS.register("time_element_rune", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> TIME_ELEMENT_RUNE_ITEM = fromBlock(TIME_ELEMENT_RUNE);


    //BLOCK ENTITIES
    public static final RegistryObject<Block> BASIC_PILL_FURNACE = BLOCKS.register("basic_pill_furnace", () -> new BasicPillFurnaceBlock(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> BASIC_PILL_FURNACE_ITEM = fromBlock(BASIC_PILL_FURNACE);
    public static final RegistryObject<BlockEntityType<BasicPillFurnaceBE>> BASIC_PILL_FURNACE_BE = BLOCK_ENTITIES.register("basic_pill_furnace", () -> BlockEntityType.Builder.of(BasicPillFurnaceBE::new, BASIC_PILL_FURNACE.get()).build(null));

    public static final RegistryObject<Block> FORMATION_CORE = BLOCKS.register("formation_core", () -> new FormationCoreBlock(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> FORMATION_CORE_ITEM = fromBlock(FORMATION_CORE);

    public static final RegistryObject<BlockEntityType<FormationCoreBE>> FORMATION_CORE_BE = BLOCK_ENTITIES.register("formation_core", () -> BlockEntityType.Builder.of(FormationCoreBE::new, FORMATION_CORE.get()).build(null));


    public static final RegistryObject<Block> ESSENCE_JAR = BLOCKS.register("essence_jar", () -> new EssenceJar(BLOCK_PROPERTIES));
    public static final RegistryObject<Item> ESSENCE_JAR_ITEM = fromBlock(ESSENCE_JAR);
    public static final RegistryObject<BlockEntityType<EssenceJarBE>> ESSENCE_JAR_BE = BLOCK_ENTITIES.register("essence_jar", () -> BlockEntityType.Builder.of(EssenceJarBE::new, ESSENCE_JAR.get()).build(null));



    //MENUS
    public static final RegistryObject<MenuType<BasicPillFurnaceMenu>> BASIC_PILL_FURNACE_MENU = CONTAINERS.register("basic_pill_furnace_menu", () -> IForgeMenuType.create(BasicPillFurnaceMenu::new));
    public static final RegistryObject<MenuType<FormationCoreMenu>> FORMATION_CORE_MENU = CONTAINERS.register("formation_core_menu", () -> IForgeMenuType.create(FormationCoreMenu::new));


    //TAGS
    public static final TagKey<Block> DOWSING_ROD_VALUABLES = TagKey.create(Registries.BLOCK, new ResourceLocation(Wuxia.MODID, "dowsing_rod_valuables"));
    public static final TagKey<Item> RING_TAG = TagKey.create(Registries.ITEM, new ResourceLocation("curios", "ring"));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }

    // Helper method to register since compiler will complain about typing otherwise
    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }
}
