package com.github.s0uldsilence.wuxia.block.essence;

import com.github.s0uldsilence.wuxia.essence.EssenceBaseStorageBE;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceJarBE extends EssenceBaseStorageBE {
    public EssenceJarBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.ESSENCE_JAR_BE.get(), pPos, pBlockState, 10000, 100, 100, 0,10);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EssenceBaseStorageBE pEntity) {
        if (level.isClientSide()) {
            return;
        }
    }
}
