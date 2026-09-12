package net.fluxedmod.gnawedmajiks.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.worldgen.biome.ModBiomes;
import net.fluxedmod.gnawedmajiks.worldgen.biome.ModCavityBiome;
import net.fluxedmod.gnawedmajiks.worldgen.biome.ModSurfaceRules;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
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
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.neoforged.fml.common.Mod;

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
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(155, 200, 31, 25))
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
                                        biomes.getOrThrow(ModBiomes.KAUPEN_VALLEY)),
                                Pair.of(Climate.parameters(0f, 0f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(ModBiomes.KAUPEN_VALLEY)),
                                Pair.of(Climate.parameters(0.1f, 0.1f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.CHERRY_GROVE)),
                                Pair.of(Climate.parameters(0.1f, 0.25f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.BEACH)),
                                Pair.of(Climate.parameters(0.1f, 0.3f, -0.05f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.NETHER));

        context.register(CAVITY_KEY, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.CAVITY_TYPE_KEY), multiBiomeGenerator));
    }

    public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        NoiseGeneratorSettings cavity = new NoiseGeneratorSettings(
                NoiseSettings.create(0, 128, 1, 2),
                Blocks.NETHERRACK.defaultBlockState(),
                Blocks.LAVA.defaultBlockState(),
                NoiseRouterData.none(),
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
}
