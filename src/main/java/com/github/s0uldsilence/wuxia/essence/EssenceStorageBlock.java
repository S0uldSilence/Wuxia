package com.github.s0uldsilence.wuxia.essence;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public abstract class EssenceStorageBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public EssenceStorageBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    //ENTITY
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);



            if (entity instanceof EssenceStorageBE tile) {


                ItemStack stack = pPlayer.getItemInHand(pHand);
                if (stack.getItem() == Items.DIAMOND) {
                    //BlockEntity entity = pLevel.getBlockEntity(pPos);
                        int added = tile.receiveEssence(1000, false);
                        if (added > 0) {
                            if (!pPlayer.isCreative())
                                stack.shrink(1);
                            pPlayer.sendSystemMessage(Component.literal("Added " + added + " essence to the storage."));
                            pPlayer.sendSystemMessage(Component.literal("Essence: "+ tile.getEssenceStored() + "/" + tile.getMaxEssenceStored()));
                            //world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                            return InteractionResult.SUCCESS;
                        }
                }
                pPlayer.sendSystemMessage(Component.literal("Essence: "+ tile.getEssenceStored() + "/" + tile.getMaxEssenceStored()));
            }
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        CompoundTag tag = pStack.getTagElement("BlockEntityTag");
        if (tag != null && tag.contains("essence") && tag.contains("essenceCapacity")) {
            int essence = tag.getInt("essence");
            int maxEssence = tag.getInt("essenceCapacity");
            int maxReceive = tag.getInt("essenceMaxReceive");
            int maxExtract = tag.getInt("essenceMaxExtract");
            pTooltip.add(Component.literal("Essence: " + essence + "/" + maxEssence));
            pTooltip.add(Component.literal("Max Receive: " + maxReceive));
            pTooltip.add(Component.literal("Max Extract: " + maxExtract));
        }
    }
}
