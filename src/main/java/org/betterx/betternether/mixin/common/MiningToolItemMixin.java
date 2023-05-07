package org.betterx.betternether.mixin.common;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DiggerItem.class)
public abstract class MiningToolItemMixin extends TieredItem implements Vanishable {
    @Shadow
    @Final
    private TagKey<Block> blocks;

    public MiningToolItemMixin(Tier tier, Properties properties) {
        super(tier, properties);
    }


    @Inject(method = "isCorrectToolForDrops", at = @At(value = "HEAD"), cancellable = true)
    private void effectiveOn(BlockState state, CallbackInfoReturnable<Boolean> info) {
        int level = this.getTier().getLevel();
        if (state.is(org.betterx.worlds.together.tag.v3.CommonBlockTags.NETHER_PORTAL_FRAME)) {
            info.setReturnValue(level >= 3 && state.is(this.blocks));
            info.cancel();
        }
    }
}
