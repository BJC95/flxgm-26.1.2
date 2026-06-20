package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider  {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
//        List<ItemLike> ZIRCON_SMELTABLES = List.of(
//                ModItems.RAW_ZIRCON,
//                ModBlocks.ZIRCON_ORE,
//                ModBlocks.DEEPSlATE_ZIRCON_ORE,
//                ModBlocks.NETHER_ZIRCON_ORE,
//                ModBlocks.END_ZIRCON_ORE
//        );
//
//        oreBlasting(ZIRCON_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.EVOKER_FANG, 0.25f, 100, "zircon" );
//        oreSmelting(ZIRCON_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.EVOKER_FANG, 0.25f, 200, "zircon" );

        shaped(RecipeCategory.MISC, ModItems.BLOOD_VIAL.get())
                .pattern(" I ")
                .pattern("RBR")
                .pattern(" R ")
                .define('I', Items.IRON_NUGGET)
                .define('R', Items.REDSTONE)
                .define('B', Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(ModItems.BLOOD_VIAL.get()), has(Items.GLASS_BOTTLE))
                .save(output);

        shapeless(RecipeCategory.COMBAT, ModItems.HYPERDONTA_BOTTLE.get())
                .requires(ModItems.FILLED_BLOOD_VIAL)
                .requires(ModItems.EVOKER_FANG,1)
                .unlockedBy(getHasName(ModItems.FILLED_BLOOD_VIAL.get()), has(ModItems.FILLED_BLOOD_VIAL.get()))
                .save(output);

        // SORROWSPRUCE
        stairBuilder(ModBlocks.SORROWSPRUCE_STAIRS.get(), Ingredient.of(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.SORROWSPRUCE_PLANKS.get()), has(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SORROWSPRUCE_SLAB.get(), ModBlocks.SORROWSPRUCE_PLANKS.get());
        buttonBuilder(ModBlocks.SORROWSPRUCE_BUTTON.get(), Ingredient.of(ModBlocks.SORROWSPRUCE_PLANKS))
                .unlockedBy(getHasName(ModBlocks.SORROWSPRUCE_PLANKS.get()), has(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .save(output);
        pressurePlate(ModBlocks.SORROWSPRUCE_PRESSURE_PLATE.get(), ModBlocks.SORROWSPRUCE_PLANKS.get());
        fenceBuilder(ModBlocks.SORROWSPRUCE_FENCE.get(), Ingredient.of(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.SORROWSPRUCE_PLANKS.get()), has(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .save(output);
        fenceGateBuilder(ModBlocks.SORROWSPRUCE_FENCE_GATE.get(), Ingredient.of(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.SORROWSPRUCE_PLANKS.get()), has(ModBlocks.SORROWSPRUCE_PLANKS.get()))
                .save(output);
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> smeltables,
            RecipeCategory craftingCategory,
            CookingBookCategory cookingCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String fromDesc
    ) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, GnawedMajiks.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));
        }
    }


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "gnawedmajiks Mod Recipes";
        }
    }
}
