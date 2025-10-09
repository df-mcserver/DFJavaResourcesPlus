package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import net.kyori.adventure.key.Key;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.font.Font;
import team.unnamed.creative.font.FontProvider;
import uk.co.nikodem.DFJavaResourcesPlus.AssetData.EmojiMap;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;
import uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders.DFEmojiTexture;
import uk.co.nikodem.DFJavaResourcesPlus.Utils.FilenameSplitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class EmojiSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "emoji";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        List<FontProvider> providers = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("assets/emojis"))) {
            for (Path path : paths.toList()) {
                File file = path.toFile();
                if (file.isFile()) {
                    FontProvider provider = doFile(resourcePack, file);
                    if (provider != null) providers.add(provider);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font.Builder font = Font.font();
        font.key(Key.key(Key.MINECRAFT_NAMESPACE, "default"));
        for (FontProvider provider : providers) {
            font.addProvider(provider);
        }
        resourcePack.font(font.build());
    }

    public FontProvider doFile(ResourcePack resourcePack, File img) {
        Writable writable = Writable.file(img);

        FilenameSplitter filename = new FilenameSplitter(img);
        if (!filename.getExtension().equals("png")) return null;

        Character character = EmojiMap.nameToUnicode.get(filename.getFilename());

        if (character == null) {
            System.out.println("Unregistered emoji \""+filename.getFilename()+"\" found! Not creating emoji..");
            return null;
        }

        return DFEmojiTexture.createEmoji(resourcePack, filename.getFilename(), character, writable);
//        System.out.println("Adding "+filename.getFilename()+"... Status: "+ DFItemTexture.createItemTexture(resourcePack, filename.getFilename(), writable, "item/generated"));
    }
}
