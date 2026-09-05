package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, GnawedMajiks.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SORROWSPRUCE_LOG.get())
                .add(ModBlocks.SORROWSPRUCE_WOOD.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get())
                .add(ModBlocks.SORROWSPRUCE_PLANKS.get())
                .add(ModBlocks.SORROWSPRUCE_SLAB.get())
                .add(ModBlocks.SORROWSPRUCE_STAIRS.get())
                .add(ModBlocks.SORROWSPRUCE_BUTTON.get())
                .add(ModBlocks.SORROWSPRUCE_PRESSURE_PLATE.get())
                .add(ModBlocks.SORROWSPRUCE_FENCE.get())
                .add(ModBlocks.SORROWSPRUCE_FENCE_GATE.get())
                .add(ModBlocks.SORROWSPRUCE_PLANKS.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SORROWSPRUCE_LOG.get())
                .add(ModBlocks.SORROWSPRUCE_WOOD.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PRI_PEDESTAL.get())
                .add(ModBlocks.SEC_PEDESTAL.get())
                .add(ModBlocks.TRI_PEDESTAL.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.SORROWSPRUCE_PLANKS.get());
        tag(BlockTags.STAIRS)
                .add(ModBlocks.SORROWSPRUCE_STAIRS.get());
        tag(BlockTags.SLABS)
                .add(ModBlocks.SORROWSPRUCE_SLAB.get());
        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.SORROWSPRUCE_PRESSURE_PLATE.get());
        tag(BlockTags.BUTTONS)
                .add(ModBlocks.SORROWSPRUCE_BUTTON.get());
        tag(BlockTags.FENCES)
                .add(ModBlocks.SORROWSPRUCE_FENCE.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.SORROWSPRUCE_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.SORROWSPRUCE_FENCE_GATE.get());
        tag(BlockTags.LOGS)
                .add(ModBlocks.SORROWSPRUCE_LOG.get())
                .add(ModBlocks.SORROWSPRUCE_WOOD.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get())
                .add(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get());
        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.SORROWSPRUCE_SAPLING.get());
    }
}
