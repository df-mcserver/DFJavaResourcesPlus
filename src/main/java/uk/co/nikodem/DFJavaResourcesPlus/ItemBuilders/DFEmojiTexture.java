package uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders;

import net.kyori.adventure.key.Key;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import static uk.co.nikodem.DFJavaResourcesPlus.Main.NAMESPACE;

public class DFEmojiTexture {
    public static FontProvider createEmoji(ResourcePack resourcePack, String name, Character unicode, Writable file) {
        Texture texture = Texture.texture()
                .key(Key.key(NAMESPACE, "emoji/"+name+".png"))
                .data(file)
                .build();

        FontProvider provider = FontProvider.bitMap()
                .file(texture.key())
                .height(8)
                .ascent(8)
                .characters(unicode.toString())
                .build();

        resourcePack.texture(texture);

        return provider;
    }
}
