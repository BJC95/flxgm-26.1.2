package net.fluxedmod.gnawedmajiks.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.worldgen.biome.ModBiomes;
import net.fluxedmod.gnawedmajiks.worldgen.biome.ModSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> CAVITY_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity"));
    public static final ResourceKey<Level> CAVITY_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity"));
    public static final ResourceKey<DimensionType> CAVITY_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity_type"));
    public static final ResourceKey<NoiseGeneratorSettings> CAVITY_NOISE_KEY = ResourceKey.create(Registries.NOISE_SETTINGS,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity"));
    private static final ResourceKey<DensityFunction> BASE_3D_NOISE_CAVITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity/base_3d_noise"));


    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        var blocks = context.lookup(Registries.BLOCK);

        context.register(CAVITY_TYPE_KEY, new DimensionType(
                false,
                true,
                false,
                false,
                1.0,
                0,
                256,
                256,
                blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD).key(),
                1.0f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, -6168523)
                        .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(2.5f))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -4212331)
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0, 0, 0, 0))
                        .build(),
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))));
    }


    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator multiBiomeGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(
                                        0f, 0f, 0f, 0f, 0f, 0f, 0f),
                                        biomes.getOrThrow(ModBiomes.DENTAL_PLAINS)),
                                Pair.of(Climate.parameters(
                                        0.3f, -0.2f, 0f, 0f, 0f, 0f, 0f),
                                        biomes.getOrThrow(ModBiomes.NEURESA)),
                                Pair.of(Climate.parameters(
                                        -0.3f, 0.2f, 0f, 0f, 0f, 0f, 0f),
                                        biomes.getOrThrow(ModBiomes.OPTIC_TUNDRA)),
                                Pair.of(Climate.parameters(
                                        0.2f, 0.3f, 0f, 0f, 0f, 0f, 0f),
                                        biomes.getOrThrow(ModBiomes.GASTRIC_SPIRE))
                        ))),
                noiseGenSettings.getOrThrow(CAVITY_NOISE_KEY));

        context.register(CAVITY_KEY, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.CAVITY_TYPE_KEY), multiBiomeGenerator));
    }

    public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        DensityFunction slide = slideNetherLike(context.lookup(Registries.DENSITY_FUNCTION), 0, 256);
        DensityFunction fullNoise = postProcess(slide);
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(
                DensityFunctions.zero(), DensityFunctions.zero(), 0.25,
                context.lookup(Registries.NOISE).getOrThrow(Noises.TEMPERATURE_NETHER)
        );
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(
                DensityFunctions.zero(), DensityFunctions.zero(), 0.25,
                context.lookup(Registries.NOISE).getOrThrow(Noises.VEGETATION_NETHER)
        );
        DensityFunction continent = DensityFunctions.shiftedNoise2d(
                DensityFunctions.zero(), DensityFunctions.zero(), 0.25,
                context.lookup(Registries.NOISE).getOrThrow(Noises.CONTINENTALNESS_LARGE)
        );
        NoiseGeneratorSettings cavity = new NoiseGeneratorSettings(
                NoiseSettings.create(0, 256, 1, 2),
                Blocks.BONE_BLOCK.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.zero(), //Barrier
                        DensityFunctions.zero(), //Fluid Floodedness
                        DensityFunctions.zero(), //Fluid Spread
                        DensityFunctions.zero(), //Lava Noise
                        temperature, //Temp
                        vegetation, //Vegetation
                        continent, //Continents
                        DensityFunctions.zero(), //Erosion
                        DensityFunctions.zero(), //Depth
                        DensityFunctions.zero(), //Ridges
                        DensityFunctions.zero(), //Preliminary Surface Level
                        fullNoise, // Final Density
                        DensityFunctions.zero(), //Vein Toggle
                        DensityFunctions.zero(), //Vein Ridged
                        DensityFunctions.zero()  //Vein Gap
                ),
                ModSurfaceRules.makeCavityRules(),
                List.of(),
                32,
                false,
                false,
                false,
                true
        );

        context.register(CAVITY_NOISE_KEY, cavity);
    }
    public static void bootstrapDensityFunction(BootstrapContext<DensityFunction> context) {
        context.register(BASE_3D_NOISE_CAVITY, BlendedNoise.createUnseeded(
                0.25, 0.375, 80.0, 60.0, 8.0));
    }

    private static DensityFunction postProcess(DensityFunction slide) {
        DensityFunction blended = DensityFunctions.blendDensity(slide);
        return DensityFunctions.mul(DensityFunctions.interpolated(blended), DensityFunctions.constant(0.64)).squeeze();
    }
    private static DensityFunction getFunction(HolderGetter<DensityFunction> functions, ResourceKey<DensityFunction> name) {
        return new DensityFunctions.HolderHolder(functions.getOrThrow(name));
    }
    private static DensityFunction slide(
            DensityFunction caves, int minY, int height, int topStartY, int topEndY, double topTarget, int bottomStartY, int bottomEndY, double bottomTarget
    ) {
        DensityFunction topFactor = DensityFunctions.yClampedGradient(minY + height - topStartY, minY + height - topEndY, 1.0, 0.0);
        DensityFunction noiseValue = DensityFunctions.lerp(topFactor, topTarget, caves);
        DensityFunction bottomFactor = DensityFunctions.yClampedGradient(minY + bottomStartY, minY + bottomEndY, 0.0, 1.0);
        return DensityFunctions.lerp(bottomFactor, bottomTarget, noiseValue);
    }
    private static DensityFunction slideNetherLike(HolderGetter<DensityFunction> functions, int minY, int height) {
        return slide(getFunction(functions, BASE_3D_NOISE_CAVITY), minY, height, 24, 0, 0.9375, -8, 24, 2.5);
    }
}
