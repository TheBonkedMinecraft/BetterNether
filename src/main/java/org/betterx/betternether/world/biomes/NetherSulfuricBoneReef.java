package org.betterx.betternether.world.biomes;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder.BiomeSupplier;
import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeSettings;
import org.betterx.bclib.api.v2.levelgen.surface.SurfaceRuleBuilder;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.registry.features.placed.NetherObjectsPlaced;
import org.betterx.betternether.registry.features.placed.NetherVegetationPlaced;
import org.betterx.betternether.registry.features.placed.NetherVinesPlaced;
import org.betterx.betternether.world.NetherBiome;
import org.betterx.betternether.world.NetherBiomeConfig;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

public class NetherSulfuricBoneReef extends NetherBiome {
    public static class Config extends NetherBiomeConfig {
        public Config(String name) {
            super(name);
        }

        @Override
        protected void addCustomBuildData(BCLBiomeBuilder builder) {
            builder.fogColor(154, 144, 49)
                   .loop(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                   .additions(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS)
                   .mood(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD)
                   .particles(ParticleTypes.ASH, 0.01F)
                   .structure(BiomeTags.HAS_NETHER_FORTRESS)
                   .feature(NetherVegetationPlaced.NETHER_REED)
                   .feature(NetherObjectsPlaced.BONES)
                   .feature(NetherObjectsPlaced.BONE_STALAGMITE)
                   .feature(NetherVegetationPlaced.VEGETATION_SULFURIC_BONE_REEF)
                   .feature(NetherVegetationPlaced.JELLYFISH_MUSHROOM)
                   .feature(NetherVinesPlaced.GOLDEN_LUMABUS_VINE)
                   .feature(NetherObjectsPlaced.STALACTITE)
                   .genChance(0.3f);
        }

        @Override
        public BiomeSupplier<NetherBiome> getSupplier() {
            return NetherSulfuricBoneReef::new;
        }

        @Override
        public boolean hasStalactites() {
            return false;
        }

        @Override
        public SurfaceRuleBuilder surface() {
            return super.surface().floor(NetherBlocks.SEPIA_MUSHROOM_GRASS.defaultBlockState());
        }
    }

    public NetherSulfuricBoneReef(ResourceLocation biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }
}
