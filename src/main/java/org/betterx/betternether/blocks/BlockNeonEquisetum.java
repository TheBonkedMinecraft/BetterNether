package org.betterx.betternether.blocks;

import org.betterx.bclib.blocks.BlockProperties;
import org.betterx.bclib.blocks.BlockProperties.TripleShape;
import org.betterx.bclib.items.tool.BaseShearsItem;
import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.blocks.materials.Materials;
import org.betterx.betternether.interfaces.SurvivesOnNetherrack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.List;

public class BlockNeonEquisetum extends BlockBaseNotFull implements BonemealableBlock, SurvivesOnNetherrack {
    protected static final VoxelShape SHAPE_SELECTION = box(2, 0, 2, 14, 16, 14);
    public static final EnumProperty<TripleShape> SHAPE = BlockProperties.TRIPLE_SHAPE;

    public BlockNeonEquisetum() {
        super(FabricBlockSettings.of(Materials.NETHER_PLANT)
                                 .mapColor(MaterialColor.COLOR_GREEN)
                                 .luminance(15)
                                 .sounds(SoundType.CROP)
                                 .noCollission()
                                 .noLootTable()
                                 .instabreak()
                                 .noOcclusion()
                                 .offsetType(OffsetType.XZ)
        );
        this.setRenderLayer(BNRenderLayer.CUTOUT);
        this.registerDefaultState(getStateDefinition().any().setValue(SHAPE, TripleShape.BOTTOM));
        setDropItself(false);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(SHAPE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        Vec3 vec3d = state.getOffset(view, pos);
        return SHAPE_SELECTION.move(vec3d.x, vec3d.y, vec3d.z);
    }

    @Environment(EnvType.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter view, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter view, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState up = world.getBlockState(pos.above());
        return up.getBlock() == this || isSurvivable(up);
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
        if (!canSurvive(state, world, pos))
            return Blocks.AIR.defaultBlockState();
        else
            return state;
    }

    @Override
    public void setPlacedBy(
            Level world,
            BlockPos pos,
            BlockState state,
            @Nullable LivingEntity placer,
            ItemStack itemStack
    ) {
        BlockPos upPos = pos.above();
        if (world.getBlockState(upPos).getBlock() == this) {
            world.setBlockAndUpdate(upPos, defaultBlockState().setValue(SHAPE, TripleShape.MIDDLE));
            upPos = upPos.above();
            if (world.getBlockState(upPos).getBlock() == this) {
                world.setBlockAndUpdate(upPos, defaultBlockState().setValue(SHAPE, TripleShape.TOP));
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        if (tool != null && BaseShearsItem.isShear(tool) || EnchantmentHelper.getItemEnchantmentLevel(
                Enchantments.SILK_TOUCH,
                tool
        ) > 0) {
            return Lists.newArrayList(new ItemStack(this.asItem()));
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        MutableBlockPos blockPos = new MutableBlockPos().set(pos);
        for (int y = pos.getY() - 1; y > 1; y--) {
            blockPos.setY(y);
            if (world.getBlockState(blockPos).getBlock() != this)
                return world.getBlockState(blockPos).getBlock() == Blocks.AIR;
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        MutableBlockPos blockPos = new MutableBlockPos().set(pos);
        for (int y = pos.getY(); y > 1; y--) {
            blockPos.setY(y);
            if (world.getBlockState(blockPos).getBlock() != this)
                break;
        }
        BlocksHelper.setWithoutUpdate(world, blockPos, this.defaultBlockState());
        this.setPlacedBy(world, blockPos, state, null, null);
    }
}
