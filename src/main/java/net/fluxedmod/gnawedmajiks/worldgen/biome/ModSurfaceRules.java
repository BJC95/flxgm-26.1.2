package net.fluxedmod.gnawedmajiks.worldgen.biome;

import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource TOOTHSLATE = makeStateRule(ModBlocks.TOOTHSLATE.get());
    private static final SurfaceRules.RuleSource SANGUINE = makeStateRule(ModBlocks.SANGUINE_TOOTHSLATE.get());
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);

    private static final SurfaceRules.RuleSource NEUROSAND = makeStateRule(ModBlocks.NEUROSAND.get());
    private static final SurfaceRules.RuleSource NEUROSANDSTONE = makeStateRule(ModBlocks.NEUROSANDSTONE.get());

    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);


    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static SurfaceRules.RuleSource makeCavityRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor",
                        VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof",
                        VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(
                        SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(64),1)),SANGUINE),

                // Then apply biome-specific rules
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.DENTAL_PLAINS),
                        SurfaceRules.sequence(
                                // Obsidian on the undersides of ceilings
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, TOOTHSLATE),
                                // Obsidian on the undersides of floors (though less common in Nether caves)
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOOTHSLATE),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, TOOTHSLATE),
                                // Default to glowstone if not under a ceiling or floor
                                TOOTHSLATE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.NEURESA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, NEUROSAND),
                                NEUROSANDSTONE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.OPTIC_TUNDRA),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLUE_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, TOOTHSLATE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOOTHSLATE),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, TOOTHSLATE),
                                TOOTHSLATE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.GASTRIC_SPIRE),
                        SurfaceRules.sequence(
                                // Obsidian on the undersides of ceilings
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, TOOTHSLATE),
                                // Obsidian on the undersides of floors (though less common in Nether caves)
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TOOTHSLATE),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, TOOTHSLATE),
                                // Default to glowstone if not under a ceiling or floor
                                TOOTHSLATE))

        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}