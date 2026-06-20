package net.fluxedmod.gnawedmajiks.block;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.custom.PriPedestalBlock;
import net.fluxedmod.gnawedmajiks.block.custom.SecPedestalBlock;
import net.fluxedmod.gnawedmajiks.block.custom.TriPedestalBlock;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GnawedMajiks.MOD_ID);
    // SORROWSPRUCE
    public static final DeferredBlock<Block> SORROWSPRUCE_PLANKS = registerBlock("sorrowspruce_planks",
            properties -> new Block(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> SORROWSPRUCE_LOG = registerBlock("sorrowspruce_log",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SORROWSPRUCE_WOOD = registerBlock("sorrowspruce_wood",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_SORROWSPRUCE_LOG = registerBlock("stripped_sorrowspruce_log",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> STRIPPED_SORROWSPRUCE_WOOD = registerBlock("stripped_sorrowspruce_wood",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> SORROWSPRUCE_STAIRS = registerBlock("sorrowspruce_stairs",
            properties -> new StairBlock(ModBlocks.SORROWSPRUCE_PLANKS.get().defaultBlockState(), properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SORROWSPRUCE_SLAB = registerBlock("sorrowspruce_slab",
            properties -> new SlabBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SORROWSPRUCE_PRESSURE_PLATE = registerBlock("sorrowspruce_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.SPRUCE, properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SORROWSPRUCE_BUTTON = registerBlock("sorrowspruce_button",
            properties -> new ButtonBlock(BlockSetType.SPRUCE, 20, properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SORROWSPRUCE_FENCE = registerBlock("sorrowspruce_fence",
            properties -> new FenceBlock(properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));
        public static final DeferredBlock<Block> SORROWSPRUCE_FENCE_GATE = registerBlock("sorrowspruce_fence_gate",
            properties -> new FenceGateBlock(WoodType.SPRUCE, properties
                    .strength(2f)
                    .explosionResistance(3f)
                    .sound(SoundType.WOOD)));

    // PEDESTALS
    public static final DeferredBlock<Block> PRI_PEDESTAL = registerBlock("primary_dental_pedestal",
            properties -> new PriPedestalBlock(properties
                    .strength(4f)
                    .explosionResistance(3f)
                    .noOcclusion()
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> SEC_PEDESTAL = registerBlock("secondary_dental_pedestal",
            properties -> new SecPedestalBlock(properties
                    .strength(4f)
                    .explosionResistance(3f)
                    .noOcclusion()
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TRI_PEDESTAL = registerBlock("tertiary_dental_pedestal",
            properties -> new TriPedestalBlock(properties
                    .strength(4f)
                    .explosionResistance(3f)
                    .noOcclusion()
                    .sound(SoundType.STONE)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name,
                properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
