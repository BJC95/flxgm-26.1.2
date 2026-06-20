package net.fluxedmod.gnawedmajiks.item;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.item.custom.BloodVialItem;
import net.fluxedmod.gnawedmajiks.item.custom.EnamelControllerItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GnawedMajiks.MOD_ID);

    public static final DeferredItem<Item> EVOKER_FANG = ITEMS.registerSimpleItem("evoker_fang",
            properties -> properties);


    public static final DeferredItem<Item> FILLED_BLOOD_VIAL = ITEMS.registerItem("blood_vial",
            properties -> new BloodVialItem(properties
                    .food(ModFoods.NO_NUTRITION, ModFoods.FILLED_BLOOD_VIAL_EFFECT)
                    .usingConvertsTo(Items.GLASS_BOTTLE)));
    public static final DeferredItem<Item> BLOOD_VIAL = ITEMS.registerItem("empty_blood_vial",
            properties -> new Item(properties
                    .food(ModFoods.NO_NUTRITION, ModFoods.BLOOD_VIAL_EFFECT)
                    .useCooldown(0.5f)
                    .usingConvertsTo(ModItems.FILLED_BLOOD_VIAL.get())
            ));
    public static final DeferredItem<Item> HYPERDONTA_BOTTLE = ITEMS.registerItem("hyperdontia_bottle",
            properties -> new Item(properties
                    .food(ModFoods.NO_NUTRITION, ModFoods.HYPERDONTIA_EFFECT)
                    .usingConvertsTo(ModItems.BLOOD_VIAL.get())
                    .stacksTo(16)
            ));


    public static final DeferredItem<Item> ENAMEL_CONTROLLER = ITEMS.registerItem("enamel_controller",
            properties -> new EnamelControllerItem(properties
                    .rarity(Rarity.EPIC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
