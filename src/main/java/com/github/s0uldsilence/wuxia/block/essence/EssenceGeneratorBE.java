package com.github.s0uldsilence.wuxia.block.essence;

import com.github.s0uldsilence.wuxia.essence.EssenceBaseGeneratorBE;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceGeneratorBE extends EssenceBaseGeneratorBE {
    public EssenceGeneratorBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.ESSENCE_GENERATOR_BE.get(), pPos, pBlockState, 10000, 500, 500, 0);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EssenceBaseGeneratorBE pEntity) {
        if (level.isClientSide()) {
            return;
        }
        pEntity.receiveEssence(10, false);
    }
}
