package net.fluxedmod.gnawedmajiks.item;

import net.fluxedmod.gnawedmajiks.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties NO_NUTRITION = new FoodProperties.Builder()
            .alwaysEdible().nutrition(0).saturationModifier(0).build();

    public static final Consumable FILLED_BLOOD_VIAL_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 400), 0.7f)).build();
    public static final Consumable BLOOD_VIAL_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 0), 1f))
                .soundAfterConsume(SoundEvents.SPEAR_HIT)
                .sound(SoundEvents.HONEY_DRINK)
                .consumeSeconds(0.4f)
                .animation(ItemUseAnimation.BLOCK)
                .build();
    public static final Consumable HYPERDONTIA_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.HYPERDONTIA, 1, 0), 1f))
                .sound(SoundEvents.HONEY_DRINK)
                .build();

}
