package com.github.s0uldsilence.wuxia.block.essence;


import com.github.s0uldsilence.wuxia.block.entity.FormationCoreBE;
import com.github.s0uldsilence.wuxia.essence.EssenceStorageBlock;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class EssenceJar extends EssenceStorageBlock {

    public EssenceJar(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EssenceJarBE(pPos, pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, Registration.ESSENCE_JAR_BE.get(),
                EssenceJarBE::tick);
    }
}
