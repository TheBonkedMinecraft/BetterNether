package org.betterx.betternether.blockentities;

import org.betterx.betternether.registry.BlockEntitiesRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityFurnace extends AbstractFurnaceBlockEntity {
    public BlockEntityFurnace(BlockPos pos, BlockState state) {
        super(BlockEntitiesRegistry.NETHERRACK_FURNACE, pos, state, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.furnace", new Object[0]);
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return new FurnaceMenu(syncId, playerInventory, this, this.dataAccess);
    }
}
