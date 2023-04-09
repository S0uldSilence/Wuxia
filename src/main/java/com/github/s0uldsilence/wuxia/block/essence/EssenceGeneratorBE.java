package com.github.s0uldsilence.wuxia.block.essence;

import com.github.s0uldsilence.wuxia.essence.EssenceBaseGeneratorBE;
import com.github.s0uldsilence.wuxia.essence.EssenceBaseStorageBE;
import com.github.s0uldsilence.wuxia.essence.IEssenceStorage;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EssenceGeneratorBE extends EssenceBaseGeneratorBE {
    public EssenceGeneratorBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.ESSENCE_GENERATOR_BE.get(), pPos, pBlockState, 10000, 15, 15, 0,1);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, EssenceBaseGeneratorBE pEntity) {
        if (level.isClientSide()) {
            return;
        }
        pEntity.receiveEssence(10, false);
        if (pEntity.getPosition(0) != null) {
            BlockPos linkedBlockPos = pEntity.getPosition(0);
            BlockEntity linkedBlockEntity = level.getBlockEntity(linkedBlockPos);
            if (linkedBlockEntity instanceof EssenceBaseStorageBE storage) {
                pEntity.extractEssence(10, false);
                storage.receiveEssence(10, false);
            }
        }
    }
}