package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, GnawedMajiks.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.SORROWSPRUCE_LOGS)
                .add(Item.byBlock(ModBlocks.SORROWSPRUCE_LOG.get()))
                .add(Item.byBlock(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get()));
        tag(ItemTags.WOODEN_TOOL_MATERIALS)
                .add(Item.byBlock(ModBlocks.SORROWSPRUCE_PLANKS.get()));
        tag(ItemTags.PLANKS)
                .add(Item.byBlock(ModBlocks.SORROWSPRUCE_PLANKS.get()));
    }
}
