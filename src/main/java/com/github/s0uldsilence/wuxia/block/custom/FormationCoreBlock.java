package com.github.s0uldsilence.wuxia.block.custom;

import com.github.s0uldsilence.wuxia.block.entity.BasicPillFurnaceBlockEntity;
import com.github.s0uldsilence.wuxia.formation.Formation;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class FormationCoreBlock extends Block {

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            // Open a screen to display the formation information
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
    public FormationCoreBlock(Properties pProperties) {
        super(pProperties);
    }
}