package org.betterx.betternether.blocks;

import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.MHelper;
import org.betterx.betternether.blocks.materials.Materials;
import org.betterx.betternether.interfaces.SurvivesOnSoulGround;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.world.features.SoulLilyFeature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.google.common.collect.Lists;

import java.util.List;

public class BlockSoulLily extends BlockBaseNotFull implements SurvivesOnSoulGround {
    public static final EnumProperty<SoulLilyShape> SHAPE = EnumProperty.create("shape", SoulLilyShape.class);

    private static final VoxelShape SHAPE_SMALL = box(6, 0, 6, 10, 16, 10);
    private static final VoxelShape SHAPE_MEDIUM_BOTTOM = box(5, 0, 5, 11, 16, 11);
    private static final VoxelShape SHAPE_MEDIUM_TOP = box(0, 0, 0, 16, 3, 16);
    private static final VoxelShape SHAPE_BIG_BOTTOM = box(3, 0, 3, 13, 16, 13);
    private static final VoxelShape SHAPE_BIG_MIDDLE = box(6, 0, 6, 10, 16, 10);
    private static final VoxelShape SHAPE_BIG_TOP_CENTER = box(0, 0, 0, 16, 4, 16);
    private static final VoxelShape SHAPE_BIG_TOP_SIDE_N = box(0, 4, 0, 16, 6, 8);
    private static final VoxelShape SHAPE_BIG_TOP_SIDE_S = box(0, 4, 8, 16, 6, 16);
    private static final VoxelShape SHAPE_BIG_TOP_SIDE_E = box(8, 4, 0, 16, 6, 16);
    private static final VoxelShape SHAPE_BIG_TOP_SIDE_W = box(0, 4, 0, 8, 6, 16);

    private static final SoulLilyFeature STRUCTURE = new SoulLilyFeature();

    private static final SoulLilyShape[] ROT = new SoulLilyShape[]{
            SoulLilyShape.BIG_TOP_SIDE_N,
            SoulLilyShape.BIG_TOP_SIDE_E,
            SoulLilyShape.BIG_TOP_SIDE_S,
            SoulLilyShape.BIG_TOP_SIDE_W
    };

    public BlockSoulLily() {
        super(Materials.makeWood(MaterialColor.COLOR_ORANGE).noOcclusion().randomTicks());
        this.registerDefaultState(getStateDefinition().any().setValue(SHAPE, SoulLilyShape.SMALL));
        this.setRenderLayer(BNRenderLayer.CUTOUT);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(SHAPE);
    }

    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        switch (state.getValue(SHAPE)) {
            case BIG_BOTTOM:
                return SHAPE_BIG_BOTTOM;
            case BIG_MIDDLE:
                return SHAPE_BIG_MIDDLE;
            case BIG_TOP_CENTER:
                return SHAPE_BIG_TOP_CENTER;
            case MEDIUM_BOTTOM:
                return SHAPE_MEDIUM_BOTTOM;
            case MEDIUM_TOP:
                return SHAPE_MEDIUM_TOP;
            case BIG_TOP_SIDE_N:
                return SHAPE_BIG_TOP_SIDE_N;
            case BIG_TOP_SIDE_S:
                return SHAPE_BIG_TOP_SIDE_S;
            case BIG_TOP_SIDE_E:
                return SHAPE_BIG_TOP_SIDE_E;
            case BIG_TOP_SIDE_W:
                return SHAPE_BIG_TOP_SIDE_W;
            case SMALL:
            default:
                return SHAPE_SMALL;
        }
    }

    public enum SoulLilyShape implements StringRepresentable {
        SMALL("small"), MEDIUM_BOTTOM("medium_bottom"), MEDIUM_TOP("medium_top"), BIG_BOTTOM("big_bottom"), BIG_MIDDLE(
                "big_middle"), BIG_TOP_CENTER("big_top_center"), BIG_TOP_SIDE_N("big_top_side_n"), BIG_TOP_SIDE_S(
                "big_top_side_s"), BIG_TOP_SIDE_E("big_top_side_e"), BIG_TOP_SIDE_W("big_top_side_w");

        final String name;

        SoulLilyShape(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public boolean canGrow(Level world, BlockPos pos, RandomSource random) {
        BlockState state = world.getBlockState(pos.below());
        if (state.getBlock() == this || state.getBlock() == Blocks.SOUL_SAND || BlocksHelper.isFertile(world.getBlockState(
                pos.below()))) {
            return BlocksHelper.isFertile(world.getBlockState(pos.below()))
                    ? (random.nextInt(8) == 0)
                    : (random.nextInt(16) == 0);
        }
        return false;
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        if (canGrow(world, pos, random)) {
            SoulLilyShape shape = state.getValue(SHAPE);
            if (shape == SoulLilyShape.SMALL && world.isEmptyBlock(pos.above())) {
                STRUCTURE.growMedium(world, pos);
            } else if (shape == SoulLilyShape.MEDIUM_BOTTOM && world.isEmptyBlock(pos.above(2)) && isAirSides(
                    world,
                    pos.above(
                            2)
            )) {
                STRUCTURE.growBig(world, pos);
            }
        }
    }

    private boolean isAirSides(Level world, BlockPos pos) {
        return world.isEmptyBlock(pos.north()) && world.isEmptyBlock(pos.south()) && world.isEmptyBlock(pos.east()) && world.isEmptyBlock(
                pos.west());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        SoulLilyShape shape = state.getValue(SHAPE);
        int index = getRotationIndex(shape);
        if (index < 0)
            return state;
        int offset = rotOffset(rotation);
        return state.setValue(SHAPE, ROT[(index + offset) & 3]);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        SoulLilyShape shape = state.getValue(SHAPE);
        int index = getRotationIndex(shape);
        if (index < 0)
            return state;
        if (mirror == Mirror.FRONT_BACK) {
            if (shape == SoulLilyShape.BIG_TOP_SIDE_E)
                shape = SoulLilyShape.BIG_TOP_SIDE_W;
            else if (shape == SoulLilyShape.BIG_TOP_SIDE_W)
                shape = SoulLilyShape.BIG_TOP_SIDE_E;
        } else if (mirror == Mirror.LEFT_RIGHT) {
            if (shape == SoulLilyShape.BIG_TOP_SIDE_N)
                shape = SoulLilyShape.BIG_TOP_SIDE_S;
            else if (shape == SoulLilyShape.BIG_TOP_SIDE_S)
                shape = SoulLilyShape.BIG_TOP_SIDE_N;
        }
        return state.setValue(SHAPE, shape);
    }

    private int getRotationIndex(SoulLilyShape shape) {
        for (int i = 0; i < 4; i++) {
            if (shape == ROT[i])
                return i;
        }
        return -1;
    }

    private int rotOffset(Rotation rotation) {
        if (rotation == Rotation.NONE)
            return 0;
        else if (rotation == Rotation.CLOCKWISE_90)
            return 1;
        else if (rotation == Rotation.CLOCKWISE_180)
            return 2;
        else
            return 3;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(NetherBlocks.SOUL_LILY_SAPLING);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        var _shape = state.getValues().get(SHAPE);
        if (_shape == null) return false;
        final SoulLilyShape shape = SHAPE.getValueClass().cast(_shape);

        if (shape == SoulLilyShape.BIG_TOP_SIDE_N)
            return world.getBlockState(pos.north()).getBlock() == this;
        if (shape == SoulLilyShape.BIG_TOP_SIDE_S)
            return world.getBlockState(pos.south()).getBlock() == this;
        if (shape == SoulLilyShape.BIG_TOP_SIDE_E)
            return world.getBlockState(pos.east()).getBlock() == this;
        if (shape == SoulLilyShape.BIG_TOP_SIDE_W)
            return world.getBlockState(pos.west()).getBlock() == this;
        BlockState down = world.getBlockState(pos.below());
        return down.getBlock() == this || isSurvivable(down);
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction facing,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        return canSurvive(state, world, pos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        switch (state.getValue(SHAPE)) {
            case BIG_BOTTOM:
            case BIG_MIDDLE:
                return Lists.newArrayList(new ItemStack(NetherBlocks.MAT_NETHER_MUSHROOM.getStem()));
            case BIG_TOP_CENTER:
                return Lists.newArrayList(
                        new ItemStack(NetherBlocks.MAT_NETHER_MUSHROOM.getStem()),
                        new ItemStack(NetherBlocks.SOUL_LILY_SAPLING)
                );
            case MEDIUM_BOTTOM:
                return Lists.newArrayList(new ItemStack(NetherBlocks.MAT_NETHER_MUSHROOM.getStem()));
            case BIG_TOP_SIDE_N:
            case BIG_TOP_SIDE_S:
            case BIG_TOP_SIDE_E:
            case BIG_TOP_SIDE_W:
                return Lists.newArrayList(
                        new ItemStack(NetherBlocks.MAT_NETHER_MUSHROOM.getStem()),
                        new ItemStack(
                                NetherBlocks.SOUL_LILY_SAPLING,
                                MHelper.randRange(0, 1, MHelper.RANDOM)
                        )
                );
            case SMALL:
            case MEDIUM_TOP:
            default:
                return Lists.newArrayList(
                        new ItemStack(NetherBlocks.MAT_NETHER_MUSHROOM.getStem()),
                        new ItemStack(NetherBlocks.SOUL_LILY_SAPLING)
                );
        }
    }
}