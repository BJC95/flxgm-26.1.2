package net.fluxedmod.gnawedmajiks.worldgen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GRIEF_PLACED_KEY = registerKey("grief_placed");
    public static final ResourceKey<PlacedFeature> SORROWSPRUCE_TREE_PLACED_KEY = registerKey("sorrowspruce_tree_placed");
    public static final ResourceKey<PlacedFeature> TOOTH_SPIKE_PLACED_KEY = registerKey("tooth_spike_placed");
    public static final ResourceKey<PlacedFeature> TEAR_SPIKE_PLACED_KEY = registerKey("tear_spike_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, GRIEF_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GRIEF_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(64), VerticalAnchor.absolute(256))));

        register(context, SORROWSPRUCE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SORROWSPRUCE_TREE_KEY),
                List.of(
                        CountPlacement.of(UniformInt.of(16,32)),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_10_10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(96), VerticalAnchor.absolute(256)),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.wouldSurvive(ModBlocks.SORROWSPRUCE_SAPLING.get().defaultBlockState(),Vec3i.ZERO))
                ));

        register(context, TOOTH_SPIKE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOOTH_SPIKE_KEY),
                List.of(
                        CountPlacement.of(UniformInt.of(4, 10)),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))
                ));
        register(context, TEAR_SPIKE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOOTH_SPIKE_KEY),
                List.of(
                        CountPlacement.of(UniformInt.of(1, 4)),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))
                ));

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
