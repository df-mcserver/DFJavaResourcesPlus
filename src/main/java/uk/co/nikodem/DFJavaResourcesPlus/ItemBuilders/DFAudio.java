package uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders;

import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.sound.Sound;

public class DFAudio {
    public static boolean createAudioFile(ResourcePack resourcePack, Writable file1, @Subst("mall") String music_disc_name) {
        Sound sound = Sound.sound(Key.key(Key.MINECRAFT_NAMESPACE, "records/"+music_disc_name), file1);

        resourcePack.sound(sound);

        return true;
    }
}
