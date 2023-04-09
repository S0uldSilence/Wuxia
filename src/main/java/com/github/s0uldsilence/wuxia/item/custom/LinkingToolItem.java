package com.github.s0uldsilence.wuxia.item.custom;

import com.github.s0uldsilence.wuxia.block.entity.BasicPillFurnaceBE;
import com.github.s0uldsilence.wuxia.essence.EssenceBaseBE;
import com.github.s0uldsilence.wuxia.essence.EssenceBaseGeneratorBE;
import com.github.s0uldsilence.wuxia.essence.EssenceBaseStorageBE;
import com.github.s0uldsilence.wuxia.screen.CultivationCheckerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LinkingToolItem extends Item {

    public LinkingToolItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        ItemStack stack = ctx.getItemInHand();
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockPos pos = ctx.getClickedPos();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        CompoundTag nbtData = new CompoundTag();

        if (!level.isClientSide) {
            //return InteractionResult.PASS;
            if(player.isShiftKeyDown()) {
                if (blockEntity != null) {
                    if (blockEntity instanceof EssenceBaseGeneratorBE) {

                        nbtData.putInt("x1", pos.getX());
                        nbtData.putInt("y1", pos.getY());
                        nbtData.putInt("z1", pos.getZ());
                        player.sendSystemMessage(Component.literal("BlockEntity at: " + "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()));
                        stack.setTag(nbtData);
                    } else if(stack.hasTag() && blockEntity instanceof EssenceBaseStorageBE) {
                        nbtData = stack.getTag();
                        int x1 = nbtData.getInt("x1");
                        int y1 = nbtData.getInt("y1");
                        int z1 = nbtData.getInt("z1");
                        BlockPos pos1 = new BlockPos(x1, y1, z1);
                        BlockEntity blockEntity1 = level.getBlockEntity(pos1);
                        if(blockEntity1 instanceof EssenceBaseGeneratorBE && blockEntity instanceof EssenceBaseStorageBE){
                            EssenceBaseGeneratorBE generatorBE = (EssenceBaseGeneratorBE) blockEntity1;
                            generatorBE.addPosition(pos);
                            player.sendSystemMessage(Component.literal("Storage linked to generator"));
                        }
                        /*nbtData.putInt("x2", pos.getX());
                        nbtData.putInt("y2", pos.getY());
                        nbtData.putInt("z2", pos.getZ());*/

                        player.sendSystemMessage(Component.literal("BlockEntity at: " + "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()));
                        //stack.setTag(nbtData);
                    }
                }
            } else {
                stack.setTag(null);
            }
        } else {

        }

        return InteractionResult.SUCCESS;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            int x1 = pStack.getTag().getInt("x1");
            int y1 = pStack.getTag().getInt("y1");
            int z1 = pStack.getTag().getInt("z1");
            pTooltipComponents.add(Component.literal("X: " + x1 + " Y: " + y1 + " Z: " + z1));
            int x2 = pStack.getTag().getInt("x2");
            int y2 = pStack.getTag().getInt("y2");
            int z2 = pStack.getTag().getInt("z2");
            pTooltipComponents.add(Component.literal("X: " + x2 + " Y: " + y2 + " Z: " + z2));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
