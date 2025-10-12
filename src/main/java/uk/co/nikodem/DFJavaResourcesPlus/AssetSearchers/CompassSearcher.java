package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import net.kyori.adventure.key.Key;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.item.Item;
import team.unnamed.creative.item.ItemModel;
import team.unnamed.creative.item.property.CompassItemNumericProperty;
import team.unnamed.creative.item.property.ItemNumericProperty;
import team.unnamed.creative.model.Model;
import team.unnamed.creative.model.ModelTexture;
import team.unnamed.creative.model.ModelTextures;
import team.unnamed.creative.texture.Texture;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;
import uk.co.nikodem.DFJavaResourcesPlus.Utils.FilenameSplitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static team.unnamed.creative.item.RangeDispatchItemModel.Entry.entry;
import static uk.co.nikodem.DFJavaResourcesPlus.Main.MINECRAFT;
import static uk.co.nikodem.DFJavaResourcesPlus.Main.NAMESPACE;

public class CompassSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "compass";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        try (Stream<Path> paths = Files.walk(Paths.get("assets/etc/locator_compass"))) {
            for (Path path : paths.toList()) {
                File file = path.toFile();
                if (file.isFile()) doFile(resourcePack, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item = Item.item(Key.key(NAMESPACE, "locator_compass"),
                ItemModel.rangeDispatch(ItemNumericProperty.compass(CompassItemNumericProperty.Target.LODESTONE)).toBuilder()
                        .addEntry(entry(0f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass16"))))
                        .addEntry(entry(0.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass17"))))
                        .addEntry(entry(1.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass18"))))
                        .addEntry(entry(2.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass19"))))
                        .addEntry(entry(3.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass20"))))
                        .addEntry(entry(4.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass21"))))
                        .addEntry(entry(5.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass22"))))
                        .addEntry(entry(6.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass23"))))
                        .addEntry(entry(7.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass24"))))
                        .addEntry(entry(8.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass25"))))
                        .addEntry(entry(9.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass26"))))
                        .addEntry(entry(10.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass27"))))
                        .addEntry(entry(11.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass28"))))
                        .addEntry(entry(12.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass29"))))
                        .addEntry(entry(13.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass30"))))
                        .addEntry(entry(14.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass31"))))
                        .addEntry(entry(15.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass0"))))
                        .addEntry(entry(16.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass1"))))
                        .addEntry(entry(17.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass2"))))
                        .addEntry(entry(18.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass3"))))
                        .addEntry(entry(19.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass4"))))
                        .addEntry(entry(20.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass5"))))
                        .addEntry(entry(21.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass6"))))
                        .addEntry(entry(22.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass7"))))
                        .addEntry(entry(23.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass8"))))
                        .addEntry(entry(24.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass9"))))
                        .addEntry(entry(25.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass10"))))
                        .addEntry(entry(26.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass11"))))
                        .addEntry(entry(27.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass12"))))
                        .addEntry(entry(28.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass13"))))
                        .addEntry(entry(29.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass14"))))
                        .addEntry(entry(30.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass15"))))
                        .addEntry(entry(31.5f, ItemModel.reference(Key.key(NAMESPACE, "item/locatorcompass16"))))
                        .scale(32f)
                        .build()
        );

        resourcePack.item(item);
    }

    public void doFile(ResourcePack resourcePack, File img) {
        Writable writable = Writable.file(img);

        FilenameSplitter filename = new FilenameSplitter(img);
        if (!filename.getExtension().equals("png")) return;

        String namespaced = "item/" + filename.getFilename();
        String namespacedTextureFile = "item/" + filename.getFilename() + ".png";

        Texture texture = Texture.texture()
                .key(Key.key(NAMESPACE, namespacedTextureFile))
                .data(writable)
                .build();

        Model model = Model.model()
                .parent(Key.key(MINECRAFT, "item/generated"))
                .key(Key.key(NAMESPACE, namespaced))
                .textures(ModelTextures.builder()
                        .addLayer(
                                ModelTexture.ofKey(Key.key(NAMESPACE, namespaced))
                        )
                        .build())
                .build();

        resourcePack.model(model);
        resourcePack.texture(texture);
    }
}
