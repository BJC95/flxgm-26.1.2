package net.fluxedmod.gnawedmajiks.dimension;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class ModNoiseGeneratorSettings {
    private static final ResourceKey<DensityFunction> BASE_3D_NOISE_NETHER = createKey("cavity/base_3d_noise");
    private static ResourceKey<DensityFunction> createKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, name));
    }
    public static final ResourceKey<NoiseGeneratorSettings> CAVITY = ResourceKey.create(Registries.NOISE_SETTINGS,
            Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity"));
    public static final NoiseSettings CAVITY_NOISE_SETTINGS = new NoiseSettings(0,256,1,1);

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        context.register(CAVITY, cavity(context));
    }

    public static NoiseGeneratorSettings cavity(BootstrapContext<?> context) {
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(
                DensityFunctions.zero(), DensityFunctions.zero(), 0.25, noises.getOrThrow(Noises.TEMPERATURE_NETHER)
        );
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(
                DensityFunctions.zero(), DensityFunctions.zero(), 0.25, noises.getOrThrow(Noises.VEGETATION_NETHER)
        );
        DensityFunction slide = slideNetherLike(functions, 0, 128);
        DensityFunction fullNoise = postProcess(slide);
        return new NoiseGeneratorSettings(
                CAVITY_NOISE_SETTINGS,
                Blocks.BONE_BLOCK.defaultBlockState(),
                Blocks.LAVA.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        temperature,
                        vegetation,
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        fullNoise,
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero()),
                SurfaceRuleData.nether(),
                List.of(),
                32,
                false,
                false,
                false,
                true
        );
    }

    private static DensityFunction slide(
            DensityFunction caves, int minY, int height, int topStartY, int topEndY, double topTarget, int bottomStartY, int bottomEndY, double bottomTarget
    ) {
        DensityFunction topFactor = DensityFunctions.yClampedGradient(minY + height - topStartY, minY + height - topEndY, 1.0, 0.0);
        DensityFunction noiseValue = DensityFunctions.lerp(topFactor, topTarget, caves);
        DensityFunction bottomFactor = DensityFunctions.yClampedGradient(minY + bottomStartY, minY + bottomEndY, 0.0, 1.0);
        return DensityFunctions.lerp(bottomFactor, bottomTarget, noiseValue);
    }
    private static DensityFunction slideNetherLike(HolderGetter<DensityFunction> functions, int minY, int height) {
        return slide(getFunction(functions, BASE_3D_NOISE_NETHER), minY, height, 24, 0, 0.9375, -8, 24, 2.5);
    }

    private static DensityFunction postProcess(DensityFunction slide) {
        DensityFunction blended = DensityFunctions.blendDensity(slide);
        return DensityFunctions.mul(DensityFunctions.interpolated(blended), DensityFunctions.constant(0.64)).squeeze();
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> functions, ResourceKey<DensityFunction> name) {
        return new DensityFunctions.HolderHolder(functions.getOrThrow(name));
    }
}
