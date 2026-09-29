package net.fluxedmod.gnawedmajiks.tag;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SORROWSPRUCE_LOGS = createTag("sorrowspruce_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> TEAR_BLOCKS = createTag("tear_blocks");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
        }

    }
}
