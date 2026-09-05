package net.fluxedmod.gnawedmajiks.worldgen.tree;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SORROWSPRUCE = new TreeGrower(GnawedMajiks.MOD_ID + ":sorrowspruce",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SORROWSPRUCE_TREE_KEY), Optional.empty());
}
