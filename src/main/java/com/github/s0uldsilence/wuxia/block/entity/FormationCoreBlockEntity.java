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
    private boolean isBoundToPlayer;
    private boolean isActive;

    public FormationCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORMATION_CORE_BLOCK_ENTITY.get(), pos, state);
    }



    public void setFormationId(int formationId) {
        this.formationId = formationId;
        setChanged();
    }

    public int getFormationId() {
        return formationId;
    }

    public boolean isBoundToPlayer() {
        return isBoundToPlayer;
    }

    public void setBoundToPlayer(boolean boundToPlayer) {
        isBoundToPlayer = boundToPlayer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("formationId", formationId);
        tag.putBoolean("isBoundToPlayer", isBoundToPlayer);
        tag.putBoolean("isActive", isActive);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        formationId = tag.getInt("formationId");
        isBoundToPlayer = tag.getBoolean("isBoundToPlayer");
        isActive = tag.getBoolean("isActive");
    }
}