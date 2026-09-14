package net.fluxedmod.gnawedmajiks.worldgen.biome;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> DENTAL_PLAINS = registerBiomeKey("dental_plains");
    public static final ResourceKey<Biome> NEURESA = registerBiomeKey("neuresa");
    public static final ResourceKey<Biome> OPTIC_TUNDRA = registerBiomeKey("optic_tundra");
    public static final ResourceKey<Biome> GASTRIC_SPIRE = registerBiomeKey("gastric_spire");
    public static final ResourceKey<Biome> ROOTMOSS = registerBiomeKey("rootmoss_grotto");
    public static final ResourceKey<Biome> ABANDONED_WEN = registerBiomeKey("abandoned_wen");
    public static final ResourceKey<Biome> SHARASS = registerBiomeKey("remains_of_sharass");

    public static void registerBiomes() {
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, DENTAL_PLAINS, ModCavityBiomes.dentalPlains(placedFeatures, carver));
        register(context, NEURESA, ModCavityBiomes.nueresa(placedFeatures, carver));
        register(context, OPTIC_TUNDRA, ModCavityBiomes.opticTundra(placedFeatures, carver));
        register(context, GASTRIC_SPIRE, ModCavityBiomes.gastricSpire(placedFeatures, carver));
        register(context, ROOTMOSS, ModCavityBiomes.gastricSpire(placedFeatures, carver));
        register(context, ABANDONED_WEN, ModCavityBiomes.gastricSpire(placedFeatures, carver));
        register(context, SHARASS, ModCavityBiomes.gastricSpire(placedFeatures, carver));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
    }
}