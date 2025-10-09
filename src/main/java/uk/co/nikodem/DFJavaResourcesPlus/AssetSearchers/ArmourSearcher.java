package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;
import uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders.DFArmourTexture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ArmourSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "armour";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        Map<String, List<File>> armours = new HashMap<>();

        try (Stream<Path> paths = Files.walk(Paths.get("assets/armour/main"))) {
            for (Path path : paths.toList()) {
                File file = path.toFile();
                if (file.isFile()) armours.put(file.getName(), List.of(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<Path> paths = Files.walk(Paths.get("assets/armour/lower"))) {
            for (Path path : paths.toList()) {
                File file = path.toFile();
                if (file.isFile()) {
                    List<File> val = armours.get(file.getName());
                    armours.replace(file.getName(), List.of(val.getFirst(), file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, List<File>> armour : armours.entrySet()) {
            if (armour.getValue() == null) continue;
            if (armour.getValue().size() != 2) continue;

            doFile(resourcePack, armour.getValue().getFirst(), armour.getValue().getLast());
        }
    }

    public void doFile(ResourcePack resourcePack, File img, File img2) {
        Writable writable1 = Writable.file(img);
        Writable writable2 = Writable.file(img2);

        String[] split_filename = img.getName().split("\\.");
        String asset_name = split_filename[0];
        String file_extension = split_filename[1].toLowerCase();

        if (!file_extension.equals("png")) return;

        String[] split_filename2 = img.getName().split("\\.");
        String asset_name2 = split_filename2[0];
        String file_extension2 = split_filename2[1].toLowerCase();

        if (!file_extension2.equals("png")) return;

        DFArmourTexture.createArmourTexture(resourcePack, asset_name, writable1, asset_name2, writable2);
//        System.out.println("Adding "+asset_name+"... Status: "+ DFArmourTexture.createArmourTexture(resourcePack, asset_name, writable1, asset_name2, writable2));
    }
}
