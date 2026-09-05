package net.fluxedmod.gnawedmajiks;

import com.mojang.logging.LogUtils;
import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.fluxedmod.gnawedmajiks.block.ModBlocks;
import net.fluxedmod.gnawedmajiks.block.entity.ModBlockEntities;
import net.fluxedmod.gnawedmajiks.creativetab.ModCreativeModeTabs;
import net.fluxedmod.gnawedmajiks.effect.ModEffects;
import net.fluxedmod.gnawedmajiks.item.ModItems;
import net.fluxedmod.gnawedmajiks.recipe.ModRecipes;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GnawedMajiks.MOD_ID)
public class GnawedMajiks {
    public static final String MOD_ID = "flx_gm";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public GnawedMajiks(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModAttachmentTypes.register(modEventBus);
        ModEffects.register(modEventBus);

        ModRecipes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SORROWSPRUCE_SAPLING.getId(), ModBlocks.POTTED_SORROWSPRUCE_SAPLING);

            // Stats.CUSTOM.get(ModStats.MANA_USED_TOTAL_STAT.get(), value -> value + " Mana");
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}