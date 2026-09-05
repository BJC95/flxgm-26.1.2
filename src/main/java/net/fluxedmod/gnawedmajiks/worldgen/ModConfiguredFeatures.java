package net.fluxedmod.gnawedmajiks.worldgen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> TEST_ORE_KEY = registerKey("test_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SORROWSPRUCE_TREE_KEY = registerKey("sorrowspruce_tree_key");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        List<OreConfiguration.TargetBlockState> testOre = List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.SORROWSPRUCE_PLANKS.get().defaultBlockState()));

        register(context, TEST_ORE_KEY, Feature.ORE, new OreConfiguration(testOre, 20));


        register(context, SORROWSPRUCE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SORROWSPRUCE_LOG.get()),
                new StraightTrunkPlacer(7, 3, 5),

                BlockStateProvider.simple(ModBlocks.SORROWSPRUCE_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(4), ConstantInt.of(1)),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

