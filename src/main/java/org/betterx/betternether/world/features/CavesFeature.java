package org.betterx.betternether.world.features;

import org.betterx.bclib.api.v2.levelgen.features.features.DefaultFeature;
import org.betterx.betternether.world.structures.StructureCaves;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CavesFeature extends DefaultFeature {
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        final RandomSource random = featurePlaceContext.random();
        final BlockPos worldPos = featurePlaceContext.origin();
        final WorldGenLevel level = featurePlaceContext.level();
        final int sx = (worldPos.getX() >> 4) << 4;
        final int sz = (worldPos.getZ() >> 4) << 4;

        caves.generate(
                level,
                new BlockPos(sx, 0, sz),
                random,
                featurePlaceContext.chunkGenerator().getGenDepth(),
                NetherThreadDataStorage.generatorForThread().context
        );
        return true;
    }

    private static StructureCaves caves;

    public static void onLoad(long seed) {
        caves = new StructureCaves(seed);
    }

    public static boolean isInCave(int x, int y, int z) {
        return caves.isInCave(x, y, z, NetherThreadDataStorage.generatorForThread().context);
    }
}
