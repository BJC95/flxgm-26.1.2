package net.fluxedmod.gnawedmajiks.effect;

import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EnamelEffect extends InstantenousMobEffect {
    public EnamelEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob.hasData(ModAttachmentTypes.ENAMEL)) {
            mob.setData(ModAttachmentTypes.ENAMEL,
                    (mob.getData(ModAttachmentTypes.ENAMEL) + 2*(amplification+1)));
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
