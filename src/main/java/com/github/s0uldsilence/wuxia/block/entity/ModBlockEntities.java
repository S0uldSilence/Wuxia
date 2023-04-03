package com.github.s0uldsilence.wuxia.block.entity;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Wuxia.MODID);

    public static final RegistryObject<BlockEntityType<BasicPillFurnaceBlockEntity>> BASIC_PILL_FURNACE =
            BLOCK_ENTITIES.register("basic_pill_furnace", () ->
                    BlockEntityType.Builder.of(BasicPillFurnaceBlockEntity::new,
                            ModBlocks.BASIC_PILL_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<FormationCoreBlockEntity>> FORMATION_CORE =
            BLOCK_ENTITIES.register("formation_core", () ->
                    BlockEntityType.Builder.of(FormationCoreBlockEntity::new,
                            ModBlocks.FORMATION_CORE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}