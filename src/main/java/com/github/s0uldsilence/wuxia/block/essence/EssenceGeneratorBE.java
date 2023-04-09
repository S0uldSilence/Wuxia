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
import java.util.Arrays;
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

        // Generate essence
        int generated = pEntity.receiveEssence(pEntity.getMaxReceive(), false);

        // Transfer essence to connected storage blocks
        List<IEssenceStorage> storages = getConnectedStorages(level, pos, pEntity);
        int remaining = generated;
        for (IEssenceStorage storage : storages) {
            int transferred = storage.receiveEssence(pEntity.getMaxExtract(), false);
            remaining -= transferred;
            if (remaining <= 0) {
                break;
            }
        }
        pEntity.extractEssence(generated - remaining, false);
    }
    private static List<IEssenceStorage> getConnectedStorages(Level level, BlockPos pos, EssenceBaseGeneratorBE generator) {
        BlockPos[] positionsArray = generator.getPositions();
        List<BlockPos> positions = new ArrayList<>(Arrays.asList(positionsArray));
        positions.add(pos); // Add own position to check for adjacent storage blocks
        return positions.stream()
                .map(level::getBlockEntity)
                .filter(be -> be instanceof IEssenceStorage)
                .map(be -> (IEssenceStorage) be)
                .collect(Collectors.toList());
    }
}