package net.fluxedmod.gnawedmajiks.item.custom;

import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.Random;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

public class BloodVialItem extends Item {
    public BloodVialItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if(entity.getOffhandItem().is(ModItems.EVOKER_FANG.get())) {
            if(!entity.hasData(ModAttachmentTypes.ENAMEL)) {
                entity.playSound(SoundEvents.EVOKER_CAST_SPELL);
                entity.setData(ModAttachmentTypes.ENAMEL, 10f);
                entity.getOffhandItem().consume(1, entity);
                double randX;
                double randY;
                double randZ;
                for (int i = 0; i < 15; i++) {
                    randX = (Math.random() - 0.5);
                    randY = (Math.random() - 0.5);
                    randZ = (Math.random() - 0.5);
                    level.addParticle(ParticleTypes.CRIT,
                            entity.getX()+randX*3, entity.getY()+randY*3+1,entity.getZ()+randZ*3,
                            randX, randY, randZ);
                }
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {

        builder.accept((Component.translatable("tooltip.flx_gm.blood_vial").append(Component.translatable("tooltip.flx_gm.blood_vial_unknown"))));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
