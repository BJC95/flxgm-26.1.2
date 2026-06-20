package net.fluxedmod.gnawedmajiks.creativetab;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GnawedMajiks.MOD_ID);

    public static final Supplier<CreativeModeTab> GNAWEDMAJIKS_TAB = CREATIVE_MODE_TABS.register("gnawedmajiks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EVOKER_FANG.get()))
                    .title(Component.translatable("creativetab.GnawedMajiks.gnawedmajikstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                      output.accept(ModItems.ENAMEL_CONTROLLER.get());
                      output.accept(ModItems.EVOKER_FANG.get());
                      output.accept(ModItems.BLOOD_VIAL.get());
                      output.accept(ModItems.FILLED_BLOOD_VIAL.get());
                      output.accept(ModItems.HYPERDONTA_BOTTLE.get());

                      output.accept(ModBlocks.SORROWSPRUCE_LOG.get());
                      output.accept(ModBlocks.SORROWSPRUCE_WOOD.get());
                      output.accept(ModBlocks.STRIPPED_SORROWSPRUCE_LOG.get());
                      output.accept(ModBlocks.STRIPPED_SORROWSPRUCE_WOOD.get());
                      output.accept(ModBlocks.SORROWSPRUCE_PLANKS.get());
                      output.accept(ModBlocks.SORROWSPRUCE_SLAB.get());
                      output.accept(ModBlocks.SORROWSPRUCE_STAIRS.get());
                      output.accept(ModBlocks.SORROWSPRUCE_PRESSURE_PLATE.get());
                      output.accept(ModBlocks.SORROWSPRUCE_BUTTON.get());
                      output.accept(ModBlocks.SORROWSPRUCE_FENCE.get());
                      output.accept(ModBlocks.SORROWSPRUCE_FENCE_GATE.get());

                      output.accept(ModBlocks.PRI_PEDESTAL.get());
                      output.accept(ModBlocks.SEC_PEDESTAL.get());
                      output.accept(ModBlocks.TRI_PEDESTAL.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
