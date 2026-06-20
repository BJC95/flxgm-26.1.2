package net.fluxedmod.gnawedmajiks.attachmenttype;

import com.mojang.serialization.Codec;
import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, GnawedMajiks.MOD_ID);

    public static final Supplier<AttachmentType<Float>> ENAMEL = ATTACHMENT_TYPES.register("enamel",
            () -> AttachmentType.builder(() -> 0f).sync(ByteBufCodecs.FLOAT)
                            .serialize(Codec.FLOAT.fieldOf("enamel")).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
