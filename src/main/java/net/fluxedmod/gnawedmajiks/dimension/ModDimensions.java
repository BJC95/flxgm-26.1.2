package net.fluxedmod.gnawedmajiks.dimension;

import com.mojang.datafixers.util.Pair;
import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> THE_CAVITY_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "the_cavity"));
    public static final ResourceKey<Level> THE_CAVITY_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "the_cavity"));
    public static final ResourceKey<DimensionType> THE_CAVITY_DIM_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "the_cavity_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);

        context.register(THE_CAVITY_DIM_TYPE_KEY, new DimensionType(
                false,
                true,
                true,
                false,
                1.0,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                0.5f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, -6168523)
                        .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(2.5f))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -6168523)
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
                                Pair.of(Climate.parameters(0f,0f,0.5f,0f,0f,0f,0f),
                                        biomes.getOrThrow(Biomes.BADLANDS)),
                                Pair.of(Climate.parameters(0.5f,0f,0f,0f,0f,0f,0f),
                                        biomes.getOrThrow(Biomes.DARK_FOREST)),
                                Pair.of(Climate.parameters(0f,0.5f,0f,0f,0f,0.5f,0f),
                                        biomes.getOrThrow(Biomes.DEEP_DARK))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.NETHER));

        context.register(THE_CAVITY_KEY, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.THE_CAVITY_DIM_TYPE_KEY), multiBiomeGenerator));
    }
}
