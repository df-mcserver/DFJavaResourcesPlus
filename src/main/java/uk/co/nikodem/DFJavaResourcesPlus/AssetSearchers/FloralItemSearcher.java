package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.metadata.animation.AnimationMeta;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;
import uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders.DFItemTexture;
import uk.co.nikodem.DFJavaResourcesPlus.Utils.FilenameSplitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FloralItemSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "floral item";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        try (Stream<Path> paths = Files.walk(Paths.get("assets/floral_items"))) {
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
        if (!filename.getExtension().equals("png")) return;

        DFItemTexture.createAnimatedItemTexture(resourcePack, filename.getFilename(), writable, "item/generated", AnimationMeta.animation().frameTime(10).width(16).height(16).frames(0, 1).build());
//        System.out.println("Adding "+filename.getFilename()+"... Status: "+ DFItemTexture.createItemTexture(resourcePack, filename.getFilename(), writable, "item/handheld"));
    }
}
