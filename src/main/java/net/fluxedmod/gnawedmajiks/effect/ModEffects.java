package net.fluxedmod.gnawedmajiks.effect;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, GnawedMajiks.MOD_ID);

    public static final Holder<MobEffect> HYPERDONTIA = MOB_EFFECTS.register("hyperdontia",
            () -> new EnamelEffect(MobEffectCategory.BENEFICIAL, 0xe6a299));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
