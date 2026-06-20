package net.fluxedmod.gnawedmajiks;

import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.fluxedmod.gnawedmajiks.block.entity.ModBlockEntities;
import net.fluxedmod.gnawedmajiks.block.entity.renderer.PriPedestalBlockEntityRenderer;
import net.fluxedmod.gnawedmajiks.block.entity.renderer.SecPedestalBlockEntityRenderer;
import net.fluxedmod.gnawedmajiks.block.entity.renderer.TriPedestalBlockEntityRenderer;
import net.fluxedmod.gnawedmajiks.keymapping.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jspecify.annotations.NonNull;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = GnawedMajiks.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = GnawedMajiks.MOD_ID, value = Dist.CLIENT)
public class GnawedMajiksClient {
    public GnawedMajiksClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        ModKeyMappings.register();
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        GnawedMajiks.LOGGER.info("HELLO FROM CLIENT SETUP");
        GnawedMajiks.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
//        event.register(ModKeyMappings.PRESS_GM_KEY.get());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
//        while(ModKeyMappings.PRESS_GM_KEY.get().consumeClick()) {
//            // CLIENT
//            Minecraft.getInstance().player.sendSystemMessage(Component.literal((
//                    String.valueOf(Minecraft.getInstance().player.getData(ModAttachmentTypes.ENAMEL)))));
//        }
    }

    @SubscribeEvent
    public static void registerHUD(RegisterGuiLayersEvent event) {
        event.registerAboveAll(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "enamel_meter"),
                ((guiGraphics, deltaTracker) -> {
                    Player player = Minecraft.getInstance().player;
            int x = guiGraphics.guiWidth() / 2;
            int y = guiGraphics.guiHeight();

            if(!player.isCreative() && !player.isSpectator()) {
                if (player.hasData(ModAttachmentTypes.ENAMEL)) {
                    Float enamel = player.getData(ModAttachmentTypes.ENAMEL);
                    String stage = getStage(enamel);

                    guiGraphics.blitSprite(
                            RenderPipelines.GUI_TEXTURED,
                            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, stage),
                            16,
                            16,
                            0,
                            0,
                            x + 97,
                            y - 20,
                            16,
                            16
                    );
                }
            }
        }));
        event.registerAbove(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "enamel_meter"),
                Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "enamel_over"),
                ((guiGraphics, deltaTracker) -> {
                    Player player = Minecraft.getInstance().player;
            int x = guiGraphics.guiWidth() / 2;
            int y = guiGraphics.guiHeight();

            if(!player.isCreative() && !player.isSpectator()) {
                if (player.hasData(ModAttachmentTypes.ENAMEL) && player.getData(ModAttachmentTypes.ENAMEL) > 10) {
                    Float enamel = player.getData(ModAttachmentTypes.ENAMEL);
                    int progress = (int)((enamel-11)/1.25);
                    guiGraphics.blitSprite(
                            RenderPipelines.GUI_TEXTURED,
                            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "enamel_wealth"),
                            16,
                            16,
                            0,
                            16-progress,
                            x + 97,
                            y - 4 - progress,
                            16,
                            progress
                    );
                    }
                }
        }));
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PRI_PEDESTAL_BE.get(), PriPedestalBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SEC_PEDESTAL_BE.get(), SecPedestalBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TRI_PEDESTAL_BE.get(), TriPedestalBlockEntityRenderer::new);
    }

    private static @NonNull String getStage(Float enamel) {
        String stage = "enamel_5";
        if (enamel >= 9) {
            stage = "enamel_0";
        } else if (enamel >= 7) {
            stage = "enamel_1";
        } else if (enamel >= 5) {
            stage = "enamel_2";
        } else if (enamel >= 3) {
            stage = "enamel_3";
        } else if (enamel >= 1) {
            stage = "enamel_4";
        }
        return stage;
    }
}
