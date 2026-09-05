package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModBlocks.SORROWSPRUCE_LOG.getId(), new FurnaceFuel(300), false);

        builder(NeoForgeDataMaps.STRIPPABLES)
                .add(ModBlocks.SORROWSPRUCE_LOG, new Strippable(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get()), false)
                .add(ModBlocks.SORROWSPRUCE_WOOD, new Strippable(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get()), false);
    }
}
