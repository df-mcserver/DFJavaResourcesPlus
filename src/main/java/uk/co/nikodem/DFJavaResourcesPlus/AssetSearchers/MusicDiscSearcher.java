package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import uk.co.nikodem.DFJavaResourcesPlus.AssetData.MusicMap;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;
import uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders.DFAudio;
import uk.co.nikodem.DFJavaResourcesPlus.Utils.FilenameSplitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MusicDiscSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "music disc";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        try (Stream<Path> paths = Files.walk(Paths.get("assets/audio/music_discs"))) {
            for (Path path : paths.toList()) {
                File file = path.toFile();
                if (file.isFile()) doFile(resourcePack, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doFile(ResourcePack resourcePack, File img) {
        Writable writable = Writable.file(img);

        FilenameSplitter filename = new FilenameSplitter(img);
        if (!filename.getExtension().equals("ogg")) return;

        if (!MusicMap.fileToName.containsKey(filename.getFilename())) {
            System.out.println("Unregistered track \""+filename.getFilename()+"\" found! Not creating music disc..");
            return;
        }

        DFAudio.createAudioFile(resourcePack, writable, MusicMap.fileToName.get(filename.getFilename()));
    }
}
