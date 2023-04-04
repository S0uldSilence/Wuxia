package com.github.s0uldsilence.wuxia.block.entity;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.formation.Formation;
import com.github.s0uldsilence.wuxia.formation.Formations;
import com.github.s0uldsilence.wuxia.screen.TileEntities.BasicPillFurnaceMenu;
import com.github.s0uldsilence.wuxia.screen.TileEntities.FormationCoreMenu;
import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class FormationCoreBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private int formationId;
    private boolean isActive;
    private int tickCounter = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;


    public FormationCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORMATION_CORE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    //case 0 -> FormationCoreBlockEntity.this.progress;
                    //case 1 -> FormationCoreBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    //case 0 -> FormationCoreBlockEntity.this.progress = value;
                    //case 1 -> FormationCoreBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        //nbt.putInt("basic_pill_furnace.progress", this.progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        //progress = nbt.getInt("basic_pill_furnace.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level level, BlockPos pos, BlockState state, FormationCoreBlockEntity pEntity) {
        if (level.isClientSide()) {
            return;
        }
        pEntity.tickCounter++;
        if (pEntity.tickCounter < 20) {
            return;
        }
        pEntity.tickCounter = 0;

        BlockPos blockAbovePos = pos.above(3); // get the position of the block 3 blocks above
        BlockState blockAboveState = level.getBlockState(blockAbovePos);
        BlockEntity blockAboveEntity = level.getBlockEntity(blockAbovePos);
        ServerLevel serverLevel = (ServerLevel) level;

        Multiblock formation = ModonomiconAPI.get().getMultiblock(new ResourceLocation(Wuxia.MODID, "placement/formation"));
        if (formation.validate(level, pos) != null) {
            // Bonemeal the area
            for (BlockPos checkPos : BlockPos.betweenClosed(blockAbovePos.offset(-2, 0, -2), blockAbovePos.offset(2, 0, 2))) {
                BlockState checkState = level.getBlockState(checkPos);
                if (checkState.getBlock() instanceof BonemealableBlock) {
                    ((BonemealableBlock) checkState.getBlock()).performBonemeal(serverLevel, RandomSource.create(), checkPos, checkState);
                }
            }
        } else {
            // if the formation is not valid, destroy the core
            //level.destroyBlock(pos, true);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("guis.wuxia.formationcore");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FormationCoreMenu(pContainerId, pPlayerInventory, this, this.data);
    }

}