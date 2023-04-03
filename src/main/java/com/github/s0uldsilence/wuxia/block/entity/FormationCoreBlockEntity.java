package com.github.s0uldsilence.wuxia.block.entity;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.formation.Formation;
import com.github.s0uldsilence.wuxia.formation.Formations;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.BonemealEvent;

import java.util.Map;
import java.util.Random;

public class FormationCoreBlockEntity extends BlockEntity {
    private int formationId;
    private boolean isActive;
    private int tickCounter = 0;

    public FormationCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORMATION_CORE.get(), pos, state);
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
        setChanged();
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("formationId", formationId);
        tag.putBoolean("isActive", isActive);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        formationId = tag.getInt("formationId");
        isActive = tag.getBoolean("isActive");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FormationCoreBlockEntity pEntity) {
        if (level.isClientSide()) {
            return;
        }
        pEntity.tickCounter++;
        if (pEntity.tickCounter < 20) {
            return;
        }
        pEntity.tickCounter = 0;

        BlockPos blockAbovePos = pos.above(3); // get the position of the block 3 blocks above
        BlockState blockAboveState = level.getBlockState(blockAbovePos);
        BlockEntity blockAboveEntity = level.getBlockEntity(blockAbovePos);
        ServerLevel serverLevel = (ServerLevel) level;
        Multiblock formation = ModonomiconAPI.get().getMultiblock(new ResourceLocation(Wuxia.MODID, "placement/formation"));
        if (formation.validate(level, pos) != null) {
            // Bonemeal the area
            for (BlockPos checkPos : BlockPos.betweenClosed(blockAbovePos.offset(-2, 0, -2), blockAbovePos.offset(2, 0, 2))) {
                BlockState checkState = level.getBlockState(checkPos);
                if (checkState.getBlock() instanceof BonemealableBlock) {
                    ((BonemealableBlock) checkState.getBlock()).performBonemeal(serverLevel, RandomSource.create(), checkPos, checkState);
                }
            }
        } else {
            // if the formation is not valid, destroy the core
            level.destroyBlock(pos, true);
        }
    }
}