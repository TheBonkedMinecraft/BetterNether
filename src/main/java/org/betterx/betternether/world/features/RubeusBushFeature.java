package org.betterx.betternether.world.features;

import org.betterx.bclib.blocks.BlockProperties.TripleShape;
import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.blocks.RubeusLog;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.world.structures.StructureGeneratorThreadContext;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RubeusBushFeature extends ContextFeature<NoneFeatureConfiguration> {
    public RubeusBushFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    protected boolean place(
            ServerLevelAccessor world,
            BlockPos pos,
            RandomSource random,
            NoneFeatureConfiguration config,
            int MAX_HEIGHT,
            StructureGeneratorThreadContext context
    ) {
        if (!world.isEmptyBlock(pos) || !world.isEmptyBlock(pos.above()) || !world.isEmptyBlock(pos.above(15)))
            return false;

        float r = random.nextFloat() * 3 + 1;
        int count = (int) r;

        for (int i = 0; i < count; i++) {
            float fr = r - i;
            int ir = (int) Math.ceil(fr);
            float r2 = fr * fr;

            int x1 = pos.getX() - ir;
            int x2 = pos.getX() + ir;
            int z1 = pos.getZ() - ir;
            int z2 = pos.getZ() + ir;

            context.POS.setY(pos.getY() + i);

            for (int x = x1; x < x2; x++) {
                context.POS.setX(x);
                int sqx = x - pos.getX();
                sqx *= sqx;
                for (int z = z1; z < z2; z++) {
                    int sqz = z - pos.getZ();
                    sqz *= sqz;
                    context.POS.setZ(z);
                    if (sqx + sqz < r2 + random.nextFloat() * r) {
                        setIfAir(
                                world,
                                context.POS,
                                NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)
                        );
                    }
                }
            }
        }

        BlocksHelper.setWithoutUpdate(world, pos, NetherBlocks.MAT_RUBEUS.getBark().defaultBlockState().setValue(
                RubeusLog.SHAPE, TripleShape.MIDDLE));
        setIfAir(world, pos.above(), NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));
        setIfAir(world, pos.north(), NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));
        setIfAir(world, pos.south(), NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));
        setIfAir(world, pos.east(), NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));
        setIfAir(world, pos.west(), NetherBlocks.RUBEUS_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));

        return true;
    }

    private void setIfAir(LevelAccessor world, BlockPos pos, BlockState state) {
        if (world.isEmptyBlock(pos))
            BlocksHelper.setWithoutUpdate(world, pos, state);
    }
}
