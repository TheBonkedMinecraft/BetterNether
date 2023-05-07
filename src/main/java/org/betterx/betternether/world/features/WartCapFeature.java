package org.betterx.betternether.world.features;

import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.world.structures.StructureGeneratorThreadContext;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WartCapFeature extends ContextFeature<NoneFeatureConfiguration> {
    private static final BlockState INSIDE = Blocks.RED_MUSHROOM_BLOCK
            .defaultBlockState()
            .setValue(HugeMushroomBlock.NORTH, false)
            .setValue(HugeMushroomBlock.SOUTH, false)
            .setValue(HugeMushroomBlock.EAST, false)
            .setValue(HugeMushroomBlock.WEST, false)
            .setValue(HugeMushroomBlock.UP, false)
            .setValue(HugeMushroomBlock.DOWN, false);
    private static final BlockState SKIN = Blocks.NETHER_WART_BLOCK
            .defaultBlockState();

    public WartCapFeature() {
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
        if (!isWall(
                world,
                pos
        ) || pos.getY() > (MAX_HEIGHT * 0.45) || pos.getY() < (MAX_HEIGHT * 0.25) || world.isEmptyBlock(
                pos.below(3)))
            return false;

        int radius = 3 + random.nextInt(3);
        int r2 = radius * radius;
        int side = radius * 2 + 1;
        int y1 = radius >> 1;
        BlockState[][][] shape = new BlockState[side][y1 + 1][side];

        for (int y = 0; y <= y1; y++) {
            context.POS.setY(pos.getY() + y);
            for (int x = -radius; x <= radius; x++) {
                context.POS.setX(pos.getX() + x);
                int sx = x + radius;
                for (int z = -radius; z <= radius; z++) {
                    context.POS.setZ(pos.getZ() + z);
                    int sz = z + radius;
                    int d = x * x + y * y * 6 + z * z;
                    if (d <= r2) {
                        if ((y == y1) || (x == -radius) || (x == radius) || (z == -radius) || (z == radius))
                            shape[sx][y][sz] = SKIN;
                        else
                            shape[sx][y][sz] = INSIDE;
                    }
                }
            }
        }

        for (int y = 0; y < y1; y++) {
            for (int x = 1; x < side - 1; x++) {
                for (int z = 1; z < side - 1; z++) {
                    if (shape[x][y][z] != null) {
                        if (shape[x - 1][y][z] == null || shape[x + 1][y][z] == null || shape[x][y][z - 1] == null || shape[x][y][z + 1] == null || shape[x][y + 1][z] == null) {
                            shape[x][y][z] = SKIN;
                        }
                    }
                }
            }
        }

        for (int y = 0; y <= y1; y++) {
            context.POS.setY(pos.getY() + y);
            for (int x = 0; x < side; x++) {
                context.POS.setX(pos.getX() + x - radius);
                for (int z = 0; z < side; z++) {
                    context.POS.setZ(pos.getZ() + z - radius);
                    if (shape[x][y][z] != null && world.isEmptyBlock(context.POS))
                        BlocksHelper.setWithoutUpdate(world, context.POS, shape[x][y][z]);
                }
            }
        }

        return true;
    }

    private boolean isWall(ServerLevelAccessor world, BlockPos pos) {
        for (Direction dir : HorizontalDirectionalBlock.FACING.getPossibleValues())
            if (world.getBlockState(pos.relative(dir)).getBlock() == Blocks.NETHER_BRICKS)
                return true;
        return false;
    }
}
