package org.betterx.betternether.interfaces;

import org.betterx.bclib.interfaces.SurvivesOnBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public interface SurvivesOnGravel extends SurvivesOnBlocks {
    List<Block> GROUND_GRAVEL = List.of(Blocks.GRAVEL);

    @Override
    default List<Block> getSurvivableBlocks() {
        return GROUND_GRAVEL;
    }
}
