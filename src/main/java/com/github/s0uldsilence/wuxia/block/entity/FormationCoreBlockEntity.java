package com.github.s0uldsilence.wuxia.block.entity;

import com.github.s0uldsilence.wuxia.formation.Formation;
import com.github.s0uldsilence.wuxia.formation.Formations;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class FormationCoreBlockEntity extends BlockEntity {
    private int formationId;
    private boolean isActive;

    public FormationCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORMATION_CORE_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean hasValidFormation() {
        Formation formation = Formations.getFormationById(formationId);
        if (formation == null) {
            return false;
        }

        BlockPos pos = getBlockPos();

        for (BlockPos relativePos : formation.getBlocks().keySet()) {
            BlockPos targetPos = pos.offset(relativePos);
            Block targetBlock = formation.getBlocks().get(relativePos);
            if (level.getBlockState(targetPos).getBlock() != targetBlock) {
                return false;
            }
        }

        return true;
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
}