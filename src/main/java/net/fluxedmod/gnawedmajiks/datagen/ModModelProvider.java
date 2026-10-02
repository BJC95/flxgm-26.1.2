package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MossyCarpetBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;

import java.util.Map;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, GnawedMajiks.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // ITEMS
        itemModels.generateFlatItem(ModItems.EVOKER_FANG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BLOOD_VIAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FILLED_BLOOD_VIAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENAMEL_CONTROLLER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HYPERDONTA_BOTTLE.get(), ModelTemplates.FLAT_ITEM);

        //BLOCKS
        blockModels.createTrivialCube(ModBlocks.TOOTHSLATE.get());
        blockModels.createTrivialCube(ModBlocks.FANG_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SANGUINE_TOOTHSLATE.get());
        blockModels.createTrivialCube(ModBlocks.CHARRED_TOOTHSLATE.get());
        blockModels.createTrivialCube(ModBlocks.FROZEN_TEARS.get());
        blockModels.createTrivialCube(ModBlocks.COMPRESSED_TEARS.get());
        blockModels.createTrivialCube(ModBlocks.NEUROSAND.get());
        blockModels.createTrivialCube(ModBlocks.GRIEF.get());
        blockModels.createTrivialCube(ModBlocks.ROOTMOSS.get());

        createTopSideBottomBlock(ModBlocks.NEUROSANDSTONE.get(), blockModels);

        blockModels.woodProvider(ModBlocks.SORROWSPRUCE_LOG.get())
                .log(ModBlocks.SORROWSPRUCE_LOG.get()).wood(ModBlocks.SORROWSPRUCE_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get())
                .log(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get()).wood(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get());

        blockModels.family(ModBlocks.SORROWSPRUCE_PLANKS.get())
                .stairs(ModBlocks.SORROWSPRUCE_STAIRS.get())
                .slab(ModBlocks.SORROWSPRUCE_SLAB.get())
                .pressurePlate(ModBlocks.SORROWSPRUCE_PRESSURE_PLATE.get())
                .button(ModBlocks.SORROWSPRUCE_BUTTON.get())
                .fence(ModBlocks.SORROWSPRUCE_FENCE.get())
                .fenceGate(ModBlocks.SORROWSPRUCE_FENCE_GATE.get());

        blockModels.createTintedLeaves(ModBlocks.SORROWSPRUCE_LEAVES.get(), TexturedModel.LEAVES, 12012255);
        blockModels.createPlantWithDefaultItem(ModBlocks.SORROWSPRUCE_SAPLING.get(),
                ModBlocks.POTTED_SORROWSPRUCE_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        createTopSideBottomBlock(ModBlocks.PRI_PEDESTAL.get(), blockModels);
        createTopSideBlock(ModBlocks.SEC_PEDESTAL.get(), blockModels);
        createTopSideBlock(ModBlocks.TRI_PEDESTAL.get(), blockModels);
    }

    public static void createTopSideBottomBlock(Block block, BlockModelGenerators blockModels) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_side"));
        blockModels.blockStateOutput
                .accept(createSimpleBlock(block, plainVariant(ModelTemplates.CUBE.create(block, mapping, blockModels.modelOutput))));
    }
    public static void createTopSideBlock(Block block, BlockModelGenerators blockModels) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_side"));
        blockModels.blockStateOutput
                .accept(createSimpleBlock(block, plainVariant(ModelTemplates.CUBE.create(block, mapping, blockModels.modelOutput))));
    }
}
