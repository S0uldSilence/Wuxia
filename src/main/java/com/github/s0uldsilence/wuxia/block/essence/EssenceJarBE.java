package com.github.s0uldsilence.wuxia.block.essence;

import com.github.s0uldsilence.wuxia.essence.EssenceStorageBE;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceJarBE extends EssenceStorageBE{
    public EssenceJarBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.ESSENCE_JAR_BE.get(), pPos, pBlockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EssenceStorageBE pEntity) {
        if (level.isClientSide()) {
            return;
        }
        //pEntity.receiveEssence(1,false);
    }
}
