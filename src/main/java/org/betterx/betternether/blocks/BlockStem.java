package org.betterx.betternether.blocks;

import org.betterx.betternether.blocks.materials.Materials;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.google.common.collect.Maps;

import java.util.EnumMap;

public class BlockStem extends BlockBaseNotFull {
    public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
    private static final EnumMap<Axis, VoxelShape> OUTLINES = Maps.newEnumMap(Axis.class);

    public BlockStem(MaterialColor color) {
        super(Materials.makeWood(color).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Axis.Y));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        return OUTLINES.get(state.getValue(AXIS));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        return OUTLINES.get(state.getValue(AXIS));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case X:
                        return state.setValue(AXIS, Axis.Z);
                    case Z:
                        return state.setValue(AXIS, Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(AXIS, ctx.getClickedFace().getAxis());
    }

    static {
        OUTLINES.put(Axis.X, box(0, 5, 5, 16, 11, 11));
        OUTLINES.put(Axis.Y, box(5, 0, 5, 11, 16, 11));
        OUTLINES.put(Axis.Z, box(5, 5, 0, 11, 11, 16));
    }
}
