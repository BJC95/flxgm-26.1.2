package net.fluxedmod.gnawedmajiks.tag;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SORROWSPRUCE_LOGS = createTag("sorrowspruce_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
        }
    }
}
