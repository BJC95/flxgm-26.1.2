package net.fluxedmod.gnawedmajiks.datagen;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundsProvider extends SoundDefinitionsProvider {
    public ModSoundsProvider(PackOutput output) {
        super(output, GnawedMajiks.MOD_ID);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.MUSIC_CAVITY.value(), definition().subtitle("sounds.flx_gm.cavity")
                .with(
                        sound(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity/bleak")),
                        sound(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity/murcury")),
                        sound(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity/sol")),
                        sound(Identifier.fromNamespaceAndPath(GnawedMajiks.MOD_ID, "cavity/tetrachromacy"))
                ));

    }
}
