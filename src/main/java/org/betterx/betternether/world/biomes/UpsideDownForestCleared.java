package org.betterx.betternether.world.biomes;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeSettings;
import org.betterx.bclib.api.v2.levelgen.surface.SurfaceRuleBuilder;
import org.betterx.bclib.api.v2.levelgen.surface.rules.RoughNoiseCondition;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.registry.NetherFeatures;
import org.betterx.betternether.registry.features.placed.NetherObjectsPlaced;
import org.betterx.betternether.registry.features.placed.NetherTreesPlaced;
import org.betterx.betternether.registry.features.placed.NetherVegetationPlaced;
import org.betterx.betternether.registry.features.placed.NetherVinesPlaced;
import org.betterx.betternether.world.NetherBiome;
import org.betterx.betternether.world.NetherBiomeConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class UpsideDownForestCleared extends NetherBiome {
    public static class Config extends NetherBiomeConfig {
        public Config(String name) {
            super(name);
        }

        @Override
        protected void addCustomBuildData(BCLBiomeBuilder builder) {
            builder.fogColor(111, 188, 121)
                   .loop(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                   .additions(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS)
                   .mood(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD)
                   .music(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST)
                   .structure(BiomeTags.HAS_BASTION_REMNANT)
                   .structure(BiomeTags.HAS_NETHER_FORTRESS)
                   .feature(NetherFeatures.NETHER_RUBY_ORE)
                   .feature(NetherTreesPlaced.ANCHOR_TREE_SPARSE)
                   .feature(NetherTreesPlaced.ANCHOR_TREE_ROOT)
                   .feature(NetherObjectsPlaced.STALAGMITE)
                   .feature(NetherVegetationPlaced.SAKURA_BUSH)
                   .feature(NetherVegetationPlaced.MOSS_COVER)
                   .feature(NetherVinesPlaced.NEON_EQUISETUM)
                   .feature(NetherVinesPlaced.WHISPERING_GOURD_VINE)
                   .feature(NetherVegetationPlaced.HOOK_MUSHROOM)
                   .feature(NetherObjectsPlaced.STALACTITE)
                   .feature(NetherVegetationPlaced.WALL_LUCIS)
                   .feature(NetherVegetationPlaced.WALL_UPSIDE_DOWN)
                   .genChance(0.5f)
            ;
        }

        @Override
        public BiomeSupplier<NetherBiome> getSupplier() {
            return UpsideDownForestCleared::new;
        }

        @Override
        public boolean hasStalactites() {
            return false;
        }


        @Override
        public boolean hasBNStructures() {
            return false;
        }

        @Override
        public boolean hasBNFeatures() {
            return false;
        }

        @Override
        public SurfaceRuleBuilder surface() {
            return super.surface().rule(
                                2,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        new RoughNoiseCondition(Noises.NETHERRACK, 0.221),
                                                        UpsideDownForest.NETHERRACK_MOSS
                                                ),
                                                SurfaceRules.state(
                                                        NetherBlocks.MUSHROOM_GRASS.defaultBlockState())
                                        )
                                )
                        )
                        .rule(
                                3,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_CEILING,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        UpsideDownForest.NOISE_CEIL_LAYER,
                                                        UpsideDownForest.CEILEING_MOSS
                                                ),
                                                NETHERRACK
                                        )
                                )
                        );
        }
    }

    public UpsideDownForestCleared(ResourceLocation biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }
}
