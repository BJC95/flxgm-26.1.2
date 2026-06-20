package net.fluxedmod.gnawedmajiks.item.custom;

import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class EnamelControllerItem extends Item {
    public EnamelControllerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            player.setData(ModAttachmentTypes.ENAMEL, player.getData(ModAttachmentTypes.ENAMEL) - 1);
        } else {
            player.setData(ModAttachmentTypes.ENAMEL, player.getData(ModAttachmentTypes.ENAMEL) + 1);
        }
        return InteractionResult.SUCCESS;
    }
}
