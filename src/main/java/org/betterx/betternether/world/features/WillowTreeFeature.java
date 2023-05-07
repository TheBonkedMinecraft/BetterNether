package org.betterx.betternether.world.features;

import org.betterx.bclib.api.v3.levelgen.features.UserGrowableFeature;
import org.betterx.bclib.blocks.BlockProperties;
import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.blocks.BNBlockProperties;
import org.betterx.betternether.blocks.BlockWillowBranch;
import org.betterx.betternether.blocks.BlockWillowLeaves;
import org.betterx.betternether.blocks.BlockWillowTrunk;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.world.structures.StructureGeneratorThreadContext;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WillowTreeFeature extends ContextFeature<NoneFeatureConfiguration> implements UserGrowableFeature<NoneFeatureConfiguration> {
    private static final Direction[] HOR = HorizontalDirectionalBlock
            .FACING
            .getPossibleValues()
            .toArray(new Direction[]{});

    public WillowTreeFeature() {
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
        if (!BlocksHelper.isNetherGround(world.getBlockState(pos.below())))
            return false;
        return grow(world, pos, random);
    }

    protected boolean grow(
            ServerLevelAccessor world,
            BlockPos pos,
            RandomSource random
    ) {
        int h2 = 5 + random.nextInt(3);

        int mh = BlocksHelper.upRay(world, pos.above(), h2);
        if (mh < 5)
            return false;

        h2 = Math.min(h2, mh);

        final Block trunk = NetherBlocks.MAT_WILLOW.getTrunk();
        BlocksHelper.setWithUpdate(
                world,
                pos,
                trunk.defaultBlockState()
                     .setValue(BlockWillowTrunk.SHAPE, BlockProperties.TripleShape.BOTTOM)
        );
        for (int h = 1; h < h2; h++)
            if (world.isEmptyBlock(pos.above(h)))
                BlocksHelper.setWithUpdate(
                        world,
                        pos.above(h),
                        trunk.defaultBlockState()
                             .setValue(BlockWillowTrunk.SHAPE, BlockProperties.TripleShape.MIDDLE)
                );
        if (world.isEmptyBlock(pos.above(h2)))
            BlocksHelper.setWithUpdate(
                    world,
                    pos.above(h2),
                    trunk.defaultBlockState()
                         .setValue(BlockWillowTrunk.SHAPE, BlockProperties.TripleShape.TOP)
            );

        for (int i = 0; i < 4; i++)
            branch(world, pos.above(h2).relative(HOR[i]), 3 + random.nextInt(2), random, HOR[i], pos.above(h2), 0);

        BlocksHelper.setWithUpdate(world, pos.above(h2 + 1), NetherBlocks.WILLOW_LEAVES.defaultBlockState().setValue(
                BlockWillowLeaves.FACING, Direction.UP).setValue(LeavesBlock.PERSISTENT, true));
        for (int i = 0; i < 4; i++)
            BlocksHelper.setWithUpdate(
                    world,
                    pos.above(h2 + 1).relative(HOR[i]),
                    NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                              .setValue(BlockWillowLeaves.FACING, HOR[i])
                                              .setValue(LeavesBlock.PERSISTENT, true)
            );

        return true;
    }

    private void branch(
            ServerLevelAccessor world,
            BlockPos pos,
            int length,
            RandomSource random,
            Direction direction,
            BlockPos center,
            int level
    ) {
        if (level > 5)
            return;
        BlockPos.MutableBlockPos bpos = new BlockPos.MutableBlockPos().set(pos);
        BlocksHelper.setWithUpdate(
                world,
                bpos,
                NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                          .setValue(BlockWillowLeaves.FACING, direction)
                                          .setValue(LeavesBlock.PERSISTENT, true)
        );
        vine(world, pos.below(), 1 + random.nextInt(1));
        Direction preDir = direction;
        int l2 = length * length;
        for (int i = 0; i < l2; i++) {
            Direction dir = random.nextInt(3) > 0
                    ? preDir
                    : random.nextBoolean()
                            ? preDir.getClockWise()
                            : preDir.getCounterClockWise();// HOR[random.nextInt(4)];
            BlockPos p = bpos.relative(dir);
            if (world.isEmptyBlock(p)) {
                bpos.set(p);
                if (bpos.distManhattan(center) > length)
                    break;
                BlocksHelper.setWithUpdate(
                        world,
                        bpos,
                        NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                                  .setValue(BlockWillowLeaves.FACING, dir)
                                                  .setValue(LeavesBlock.PERSISTENT, true)
                );

                if (random.nextBoolean()) {
                    BlocksHelper.setWithUpdate(
                            world,
                            bpos.above(),
                            NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                                      .setValue(
                                                              BlockWillowLeaves.FACING,
                                                              Direction.UP
                                                      )
                                                      .setValue(LeavesBlock.PERSISTENT, true)
                    );
                }

                if (random.nextInt(3) == 0) {
                    bpos.setY(bpos.getY() - 1);
                    BlocksHelper.setWithUpdate(
                            world,
                            bpos,
                            NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                                      .setValue(
                                                              BlockWillowLeaves.FACING,
                                                              Direction.DOWN
                                                      )
                                                      .setValue(LeavesBlock.PERSISTENT, true)
                    );
                }

                if (random.nextBoolean())
                    vine(world, bpos.below(), 1 + random.nextInt(4));

                if (random.nextBoolean()) {
                    Direction right = dir.getClockWise();
                    BlockPos p2 = bpos.relative(right);
                    if (world.isEmptyBlock(p2))
                        branch(world, p2, length, random, right, center, level + 1);
                    right = right.getOpposite();
                    p2 = bpos.relative(right);
                    if (world.isEmptyBlock(p2))
                        branch(world, p2, length, random, right, center, level + 1);
                }

                Direction dir2 = HOR[random.nextInt(4)];
                BlockPos p2 = bpos.relative(dir2);
                if (world.isEmptyBlock(p2))
                    BlocksHelper.setWithUpdate(
                            world,
                            p2,
                            NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                                      .setValue(BlockWillowLeaves.FACING, dir2)
                                                      .setValue(LeavesBlock.PERSISTENT, true)
                    );

                preDir = dir;
            }
        }

        if (random.nextBoolean()) {
            if (world.isEmptyBlock(bpos))
                BlocksHelper.setWithUpdate(
                        world,
                        bpos,
                        NetherBlocks.WILLOW_LEAVES.defaultBlockState()
                                                  .setValue(BlockWillowLeaves.FACING, preDir)
                );
        }
    }

    private void vine(ServerLevelAccessor world, BlockPos pos, int length) {
        if (!world.isEmptyBlock(pos))
            return;

        final Block branch = NetherBlocks.MAT_WILLOW.getBranch();
        for (int i = 0; i < length; i++) {
            BlockPos p = pos.below(i);
            if (world.isEmptyBlock(p.below()))
                BlocksHelper.setWithUpdate(
                        world,
                        p,
                        branch.defaultBlockState()
                              .setValue(
                                      BlockWillowBranch.SHAPE,
                                      BNBlockProperties.WillowBranchShape.MIDDLE
                              )
                );
            else {
                BlocksHelper.setWithUpdate(
                        world,
                        p,
                        branch.defaultBlockState()
                              .setValue(
                                      BlockWillowBranch.SHAPE,
                                      BNBlockProperties.WillowBranchShape.END
                              )
                );
                return;
            }
        }
        BlocksHelper.setWithUpdate(
                world,
                pos.below(length),
                branch.defaultBlockState()
                      .setValue(BlockWillowBranch.SHAPE, BNBlockProperties.WillowBranchShape.END)
        );
    }

    @Override
    public boolean grow(
            ServerLevelAccessor level,
            BlockPos pos,
            RandomSource random,
            NoneFeatureConfiguration configuration
    ) {
        return grow(level, pos, random);
    }
}
