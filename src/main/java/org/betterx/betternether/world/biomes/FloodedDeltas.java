package org.betterx.betternether.world.biomes;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeSettings;
import org.betterx.bclib.api.v2.levelgen.surface.SurfaceRuleBuilder;
import org.betterx.bclib.api.v2.levelgen.surface.rules.SwitchRuleSource;
import org.betterx.betternether.registry.features.placed.NetherObjectsPlaced;
import org.betterx.betternether.registry.features.placed.NetherTerrainPlaced;
import org.betterx.betternether.world.NetherBiome;
import org.betterx.betternether.world.NetherBiomeConfig;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public class FloodedDeltas extends NetherBiome {
    public static class Config extends NetherBiomeConfig {
        public Config(String name) {
            super(name);
        }

        @Override
        protected void addCustomBuildData(BCLBiomeBuilder builder) {
            builder.fogColor(104, 95, 112)
                   .loop(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                   .additions(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS)
                   .mood(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD)
                   .music(SoundEvents.MUSIC_BIOME_BASALT_DELTAS)
                   .particles(ParticleTypes.WHITE_ASH, 0.12F)
                   .structure(BiomeTags.HAS_NETHER_FORTRESS)
                   .feature(NetherTerrainPlaced.FLOODED_LAVA_PIT)
                   .feature(NetherObjectsPlaced.BASALT_STALAGMITE)
                   .feature(NetherObjectsPlaced.BLACKSTONE_STALAGMITE)
                   .feature(NetherObjectsPlaced.BASALT_STALACTITE)
                   .feature(NetherObjectsPlaced.BLACKSTONE_STALACTITE)
                   .genChance(0.3f)
            ;
        }

        @Override
        public BiomeSupplier<NetherBiome> getSupplier() {
            return FloodedDeltas::new;
        }

        @Override
        public boolean hasStalactites() {
            return false;
        }

        @Override
        public boolean hasNetherCity() {
            return false;
        }

        @Override
        public SurfaceRuleBuilder surface() {
            return super
                    .surface()
                    .ceil(Blocks.DEEPSLATE.defaultBlockState())
                    .rule(new SwitchRuleSource(
                            NetherGrasslandsNumericProvider.DEFAULT,
                            List.of(
                                    SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState()),
                                    SurfaceRules.state(Blocks.BLACKSTONE.defaultBlockState())
                            )
                    ));
        }
    }


    public FloodedDeltas(ResourceLocation biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }
}
