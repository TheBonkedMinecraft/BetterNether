package org.betterx.betternether.world.features;

import org.betterx.bclib.api.v3.levelgen.features.UserGrowableFeature;
import org.betterx.bclib.blocks.BlockProperties;
import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.MHelper;
import org.betterx.betternether.blocks.BlockAnchorTreeVine;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.world.structures.StructureGeneratorThreadContext;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Iterator;
import java.util.Map;

public class AnchorTreeBranchFeature extends ContextFeature<NoneFeatureConfiguration> implements UserGrowableFeature<NoneFeatureConfiguration> {
    private static final float[] CURVE_X = new float[]{9F, 7F, 1.5F, 0.5F, 3F, 7F};
    private static final float[] CURVE_Y = new float[]{-20F, -17F, -12F, -4F, 0F, 2F};
    private static final int MIDDLE_Y = 10;

    public AnchorTreeBranchFeature() {
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
        final float scale_factor = MAX_HEIGHT / 128.0f;
        if (pos.getY() < 56 + random.nextInt((int) (20 * scale_factor))) return false;
        return grow(world, pos, random, scale_factor, context);
    }

    private boolean grow(
            ServerLevelAccessor world,
            BlockPos pos,
            RandomSource random,
            float scale_factor,
            StructureGeneratorThreadContext context
    ) {
        context.clear();
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 0);
        float scale = MHelper.randRange(0.5F, 1F, random);
        int minCount = scale < 0.75 ? 3 : 4;
        int maxCount = scale < 0.75 ? 5 : 7;
        int count = MHelper.randRange(minCount, maxCount, random);

        final BlockState leaves = NetherBlocks.ANCHOR_TREE_LEAVES.defaultBlockState()
                                                                 .setValue(LeavesBlock.PERSISTENT, true);
        final Direction[] directions = {
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.DOWN,
                Direction.UP
        };

        for (int n = 0; n < count; n++) {
            float branchSize = MHelper.randRange(0.5F, 0.8F, random) * scale;
            float angle = n * MHelper.PI2 / count;
            float radius = CURVE_X[0] * branchSize * scale_factor;
            int x1 = Math.round(pos.getX() + radius * (float) Math.cos(angle) + MHelper.randRange(
                    -2F,
                    2F,
                    random
            ) * branchSize);
            int y1 = Math.round(pos.getY() + CURVE_Y[0] * branchSize + MHelper.randRange(-2F, 2F, random) * branchSize);
            int z1 = Math.round(pos.getZ() + radius * (float) Math.sin(angle) + MHelper.randRange(
                    -2F,
                    2F,
                    random
            ) * branchSize);
            float crownR = 9 * branchSize;
            if (crownR < 1.5F)
                crownR = 1.5F;
            crown(world, new BlockPos(x1, y1 + 1, z1), crownR, random, scale_factor, context);

            int middle = Math.round(pos.getY() + (MIDDLE_Y + MHelper.randRange(-2, 2, random)) * branchSize);
            boolean generate = true;
            for (int i = 1; i < CURVE_X.length && generate; i++) {
                radius = CURVE_X[i] * branchSize;
                int x2 = Math.round(pos.getX() + radius * (float) Math.cos(angle) + MHelper.randRange(
                        -2F,
                        2F,
                        random
                ) * branchSize);
                int y2 = Math.round(pos.getY() + CURVE_Y[i] * branchSize + (CURVE_Y[i] > 0
                        ? MHelper.randRange(
                        -2F,
                        2F,
                        random
                ) * branchSize
                        : 0));
                int z2 = Math.round(pos.getZ() + radius * (float) Math.sin(angle) + MHelper.randRange(
                        -2F,
                        2F,
                        random
                ) * branchSize);

                if (CURVE_Y[i] >= 0) {
                    if (canReplace(world.getBlockState(context.POS.set(x2, y2, z2)))) {
                        boolean noGround = true;
                        for (int d = 1; d < 3; d++) {
                            if (!canReplace(world.getBlockState(context.POS.set(x2, y2 - d, z2)))) {
                                y2 -= d;
                                noGround = false;
                                break;
                            }
                        }
                        if (noGround) {
                            x2 = pos.getX();
                            y2 = pos.getY();
                            generate = false;
                        }
                    }
                }

                line(world, x1, y1, z1, x2, y2, z2, middle, context);
                x1 = x2;
                y1 = y2;
                z1 = z2;
            }
        }

        BlockState state;
        Iterator<BlockPos> iterator = context.TOP.iterator();
        while (iterator.hasNext()) {
            BlockPos bpos = iterator.next();
            if (bpos != null) {
                if (context.POINTS.contains(bpos.above()) && !context.TOP.contains(bpos.above()))
                    iterator.remove();
            }
        }

        iterator = context.MIDDLE.iterator();
        while (iterator.hasNext()) {
            BlockPos bpos = iterator.next();
            if (bpos != null) {
                BlockPos up = bpos.above();
                if (context.MIDDLE.contains(up) || (!context.TOP.contains(up) && context.POINTS.contains(up)))
                    iterator.remove();
            } else
                iterator.remove();
        }


        for (BlockPos bpos : context.POINTS) {
            if (context.POINTS.contains(bpos.above()) && context.POINTS.contains(bpos.below()))
                state = NetherBlocks.MAT_ANCHOR_TREE.getLog().defaultBlockState();
            else
                state = NetherBlocks.MAT_ANCHOR_TREE.getBark().defaultBlockState();

            BlocksHelper.setWithUpdate(world, bpos, state);

            boolean hasLeavesOnLevel = false;
            for (Direction d : directions) {
                //do not fill down on the stem
                if (!hasLeavesOnLevel && d == Direction.DOWN) continue;
                int max = 0;

                //find the last leave in the current direction within reach
                for (int i = 1; i <= LeavesBlock.DECAY_DISTANCE; i++) {
                    context.POS.setWithOffset(bpos, d.getStepX() * i, d.getStepY() * i, d.getStepZ() * i);
                    BlockState currentState = world.getBlockState(context.POS);
                    if (currentState.hasProperty(BlockStateProperties.DISTANCE)) {
                        max = i;
                    }
                }

                //check if this y-level has any leaves
                if (d != Direction.DOWN && d != Direction.UP) {
                    hasLeavesOnLevel |= max > 0;
                }

                //fill any airpockets/veins with leaves
                for (int i = 1; i < max; i++) {
                    context.POS.setWithOffset(bpos, d.getStepX() * i, d.getStepY() * i, d.getStepZ() * i);
                    BlockState currentState = world.getBlockState(context.POS);
                    if (currentState.is(NetherBlocks.ANCHOR_TREE_VINE) || currentState.is(Blocks.AIR)) {
                        BlocksHelper.setWithUpdate(world, context.POS, leaves);
                        //safeSet(world, mutableBlockPos, Blocks.WHITE_CONCRETE.defaultBlockState());

                        //replace upward veins with leaves
                        BlockPos vpos = context.POS.above();
                        currentState = world.getBlockState(vpos);

                        while (currentState.is(NetherBlocks.ANCHOR_TREE_VINE)) {
                            safeSet(world, vpos, leaves);
                            //safeSet(world, vpos, Blocks.YELLOW_WOOL.defaultBlockState());
                            vpos = vpos.above();
                            currentState = world.getBlockState(vpos);
                        }
                    }
                }
            }
            updateSDFFrom(bpos, context);
        }

        context.clear();

        updateDistances(world, context);

//		final Block[] woool = {
//			Blocks.BLACK_WOOL,
//			Blocks.BLUE_WOOL,
//			Blocks.CYAN_WOOL,
//			Blocks.GREEN_WOOL,
//			Blocks.LIME_WOOL,
//			Blocks.YELLOW_WOOL,
//			Blocks.ORANGE_WOOL,
//			Blocks.RED_WOOL
//		};
//
//		for (Entry<BlockPos, Byte> entry : LOGS_DIST.entrySet()){
//			final int dist = entry.getValue();
//			final BlockPos logPos = entry.getKey();
//			BlockState currentState = world.getBlockState(logPos);
//			if (currentState.hasProperty(BlockStateProperties.DISTANCE) ) {
//				safeSet(world, logPos, woool[dist].defaultBlockState());
//			}
//		}

        context.LOGS_DIST.clear();

        return true;
    }

    public static void safeSet(LevelAccessor level, BlockPos pos, BlockState state) {
        if (!state.is(Blocks.BEDROCK)) {
            BlocksHelper.setWithoutUpdate(level, pos, state);
        }
    }

    private void updateDistances(ServerLevelAccessor world, StructureGeneratorThreadContext context) {
        for (Map.Entry<BlockPos, Byte> entry : context.LOGS_DIST.entrySet()) {
            final int dist = entry.getValue();
            final BlockPos logPos = entry.getKey();

            BlockState currentState = world.getBlockState(logPos);
            if (currentState.hasProperty(BlockStateProperties.DISTANCE)) {
                int cDist = currentState.getValue(BlockStateProperties.DISTANCE);
                if (dist < cDist) {
                    safeSet(world, logPos, currentState.setValue(BlockStateProperties.DISTANCE, dist));
                    cDist = dist;
                }

                if (cDist >= 7) {
                    safeSet(world, logPos, Blocks.AIR.defaultBlockState());
                    //safeSet(world, logPos, Blocks.ORANGE_WOOL.defaultBlockState());

                    BlockPos pos = logPos.below();
                    currentState = world.getBlockState(pos);

                    while (currentState.is(NetherBlocks.ANCHOR_TREE_VINE)) {
                        safeSet(world, pos, Blocks.AIR.defaultBlockState());
                        //safeSet(world, pos, Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState());
                        pos = pos.below();
                        currentState = world.getBlockState(pos);
                    }
                }
            }
        }
    }

    private void updateSDFFrom(BlockPos bpos, StructureGeneratorThreadContext context) {
        for (int x = -7; x <= 7; x++) {
            for (int y = -7; y <= 7; y++) {
                for (int z = -7; z <= 7; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    final int dist = Math.abs(x) + Math.abs(y) + Math.abs(z);
                    if (dist <= 7) {
                        final BlockPos blPos = bpos.offset(x, y, z);
                        context.LOGS_DIST.merge(
                                blPos,
                                (byte) dist,
                                (oldDist, newDist) -> (byte) Math.min(oldDist, dist)
                        );
                    }
                }
            }
        }
    }

    private void line(
            LevelAccessor world,
            int x1,
            int y1,
            int z1,
            int x2,
            int y2,
            int z2,
            int middleY,
            StructureGeneratorThreadContext context
    ) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;
        int mx = Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz));
        float fdx = (float) dx / mx;
        float fdy = (float) dy / mx;
        float fdz = (float) dz / mx;
        float px = x1;
        float py = y1;
        float pz = z1;

        BlockPos pos = context.POS.set(x1, y1, z1).immutable();
        context.POINTS.add(pos);
        if (pos.getY() == middleY)
            context.MIDDLE.add(pos);
        else if (pos.getY() > middleY)
            context.TOP.add(pos);

        pos = context.POS.set(x2, y2, z2).immutable();
        context.POINTS.add(pos);
        if (pos.getY() == middleY)
            context.MIDDLE.add(pos);
        else if (pos.getY() > middleY)
            context.TOP.add(pos);

        for (int i = 0; i < mx; i++) {
            px += fdx;
            py += fdy;
            pz += fdz;

            context.POS.set(Math.round(px), Math.round(py), Math.round(pz));
            pos = context.POS.immutable();
            context.POINTS.add(pos);
            if (context.POS.getY() == middleY)
                context.MIDDLE.add(pos);
            else if (context.POS.getY() > middleY)
                context.TOP.add(pos);
        }
    }

    private void crown(
            LevelAccessor world,
            BlockPos pos,
            float radius,
            RandomSource random,
            float scale_factor,
            StructureGeneratorThreadContext context
    ) {
        scale_factor = (scale_factor - 1) * 0.25f + 1;

        final int HEIGHT_10;
        final int HEIGHT_15;
        final int HEIGHT_17;

        float rnd = MHelper.nextFloat(random, 5 * scale_factor);
        HEIGHT_10 = (int) (10 * scale_factor + rnd);
        HEIGHT_15 = (int) (15 * scale_factor + rnd);
        HEIGHT_17 = (int) (17 * scale_factor + rnd);

        BlockState leaves = NetherBlocks.ANCHOR_TREE_LEAVES.defaultBlockState();
        BlockState vine = NetherBlocks.ANCHOR_TREE_VINE.defaultBlockState();

        final float halfR = radius * 0.5F;
        final float r2 = radius * radius;
        final int start = (int) Math.floor(-radius);

        for (int cy = (int) Math.floor(radius); cy >= start; cy--) {
            int cy2_out = cy * cy;
            float cy2_in = cy + halfR;
            cy2_in *= cy2_in;
            context.POS.setY((int) (pos.getY() + cy - halfR));
            for (int cx = start; cx <= radius; cx++) {
                int cx2 = cx * cx;
                context.POS.setX(pos.getX() + cx);
                for (int cz = start; cz <= radius; cz++) {
                    int cz2 = cz * cz;
                    if (cx2 + cy2_out + cz2 < r2 && cx2 + cy2_in + cz2 > r2) {
                        context.POS.setZ(pos.getZ() + cz);
                        if (world.getBlockState(context.POS).getMaterial().isReplaceable()) {
                            int length = BlocksHelper.downRay(world, context.POS, HEIGHT_17);
                            if (length < 5) {
                                safeSet(world, context.POS, leaves);
                                continue;
                            }
                            if (length > HEIGHT_15) length = MHelper.randRange(HEIGHT_10, HEIGHT_15, random);
                            else if (length > HEIGHT_10) length = MHelper.randRange(HEIGHT_10, length, random);

                            if (cz % 2 == cx % 2) {
                                length /= 3;
                            }

                            if (length > 4) {
                                for (int i = 1; i < length - 2; i++) {
                                    safeSet(world, context.POS.below(i), vine);
                                }
                                safeSet(
                                        world,
                                        context.POS.below(length - 2),
                                        vine.setValue(BlockAnchorTreeVine.SHAPE, BlockProperties.TripleShape.MIDDLE)
                                );
                                safeSet(
                                        world,
                                        context.POS.below(length - 1),
                                        vine.setValue(BlockAnchorTreeVine.SHAPE, BlockProperties.TripleShape.BOTTOM)
                                );
                            }
                            safeSet(world, context.POS, leaves);
                        }
                    }
                }
            }
        }
    }

    private boolean canReplace(BlockState state) {
        return BlocksHelper.isNetherGround(state) || state.getMaterial().isReplaceable();
    }

    @Override
    public boolean grow(ServerLevelAccessor level, BlockPos pos, RandomSource random, NoneFeatureConfiguration cfg) {
        return grow(level, pos, random, 1, NetherThreadDataStorage.generatorForThread().context);
    }
}
