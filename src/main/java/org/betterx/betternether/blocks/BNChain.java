package org.betterx.betternether.blocks;

import org.betterx.betternether.client.IRenderTypeable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import java.util.Collections;
import java.util.List;

public class BNChain extends ChainBlock implements IRenderTypeable {
    public BNChain() {
        super(FabricBlockSettings.copyOf(Blocks.CHAIN));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(this.asItem()));
    }

    @Override
    public BNRenderLayer getRenderLayer() {
        return BNRenderLayer.CUTOUT;
    }
}
