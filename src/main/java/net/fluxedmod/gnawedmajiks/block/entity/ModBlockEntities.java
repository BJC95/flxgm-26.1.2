package net.fluxedmod.gnawedmajiks.block.entity;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.block.entity.custom.SecPedestalBlockEntity;
import net.fluxedmod.gnawedmajiks.block.entity.custom.PriPedestalBlockEntity;
import net.fluxedmod.gnawedmajiks.block.entity.custom.TriPedestalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, GnawedMajiks.MOD_ID);

    public static final Supplier<BlockEntityType<PriPedestalBlockEntity>> PRI_PEDESTAL_BE =
            BLOCK_ENTITIES.register("pri_pedestal_be", () -> new BlockEntityType<>(
                    PriPedestalBlockEntity::new, ModBlocks.PRI_PEDESTAL.get()));
    public static final Supplier<BlockEntityType<SecPedestalBlockEntity>> SEC_PEDESTAL_BE =
            BLOCK_ENTITIES.register("sec_pedestal_be", () -> new BlockEntityType<>(
                    SecPedestalBlockEntity::new, ModBlocks.SEC_PEDESTAL.get()));
    public static final Supplier<BlockEntityType<TriPedestalBlockEntity>> TRI_PEDESTAL_BE =
            BLOCK_ENTITIES.register("tri_pedestal_be", () -> new BlockEntityType<>(
                    TriPedestalBlockEntity::new, ModBlocks.TRI_PEDESTAL.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
