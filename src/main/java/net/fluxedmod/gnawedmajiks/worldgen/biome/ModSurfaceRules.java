package net.fluxedmod.gnawedmajiks.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource BONE = makeStateRule(Blocks.BONE_BLOCK);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);

    private static final SurfaceRules.RuleSource GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static SurfaceRules.RuleSource makeCavityRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor",
                        VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof",
                        VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),

                // Then apply biome-specific rules
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.KAUPEN_VALLEY),
                        SurfaceRules.sequence(
                                // Obsidian on the undersides of ceilings
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BONE),
                                // Obsidian on the undersides of floors (though less common in Nether caves)
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, NETHERRACK),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, BONE),
                                // Default to glowstone if not under a ceiling or floor
                                NETHERRACK))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}