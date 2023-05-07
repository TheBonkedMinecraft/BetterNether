package org.betterx.betternether.world.biomes;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeSettings;
import org.betterx.bclib.api.v2.levelgen.surface.SurfaceRuleBuilder;
import org.betterx.bclib.api.v2.levelgen.surface.rules.Conditions;
import org.betterx.betternether.registry.features.placed.NetherObjectsPlaced;
import org.betterx.betternether.registry.features.placed.NetherTerrainPlaced;
import org.betterx.betternether.registry.features.placed.NetherVegetationPlaced;
import org.betterx.betternether.registry.features.placed.NetherVinesPlaced;
import org.betterx.betternether.world.NetherBiome;
import org.betterx.betternether.world.NetherBiomeConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class NetherMagmaLand extends NetherBiome {
    public static class Config extends NetherBiomeConfig {
        public Config(String name) {
            super(name);
        }

        @Override
        protected void addCustomBuildData(BCLBiomeBuilder builder) {
            builder.fogColor(248, 158, 68)
                   .loop(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                   .additions(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS)
                   .mood(SoundEvents.AMBIENT_NETHER_WASTES_MOOD)
                   .structure(BiomeTags.HAS_NETHER_FORTRESS)
                   .feature(NetherTerrainPlaced.LAVA_PITS_DENSE)
                   .feature(NetherTerrainPlaced.MAGMA_BLOBS)
                   .feature(NetherObjectsPlaced.OBSIDIAN_CRYSTAL)
                   .feature(NetherObjectsPlaced.STALAGMITE)
                   .feature(NetherVegetationPlaced.BLACK_BUSH)
                   .feature(NetherVegetationPlaced.VEGETATION_MAGMA_LAND)
                   .feature(NetherVinesPlaced.GOLDEN_VINE)
                   .feature(NetherObjectsPlaced.STALACTITE)
            ;
        }


        @Override
        public BiomeSupplier<NetherBiome> getSupplier() {
            return NetherMagmaLand::new;
        }

        @Override
        public SurfaceRuleBuilder surface() {
            return super
                    .surface()
                    .chancedFloor(
                            Blocks.MAGMA_BLOCK.defaultBlockState(),
                            SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(
                                            Conditions.NETHER_VOLUME_NOISE,
                                            SurfaceRules.sequence(
                                                    SurfaceRules.ifTrue(
                                                            SurfaceRules.hole(),
                                                            SurfaceRules.state(Blocks.NETHERRACK.defaultBlockState())
                                                    ),
                                                    SurfaceRules.state(Blocks.RED_SAND.defaultBlockState())
                                            )
                                    ),
                                    SurfaceRules.state(Blocks.NETHERRACK.defaultBlockState())
                            ),
                            Conditions.NETHER_SURFACE_NOISE
                    );
        }
    }

    public NetherMagmaLand(ResourceLocation biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }
}
