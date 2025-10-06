package uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders;

import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.item.Item;
import team.unnamed.creative.item.ItemModel;
import team.unnamed.creative.model.Model;
import team.unnamed.creative.model.ModelTexture;
import team.unnamed.creative.model.ModelTextures;
import team.unnamed.creative.texture.Texture;

import static uk.co.nikodem.DFJavaResourcesPlus.Main.MINECRAFT;
import static uk.co.nikodem.DFJavaResourcesPlus.Main.NAMESPACE;

public class DFItemTexture {
    public static boolean createItemTexture(ResourcePack resourcePack, @Subst("example_item") String itemName, Writable file) {

        String namespaced = "item/"+itemName;
        String namespacedTextureFile = "item/"+itemName+".png";

        Texture texture = Texture.texture()
                .key(Key.key(NAMESPACE, namespacedTextureFile))
                .data(file)
                .build();

        Model model = Model.model()
                .parent(Key.key(MINECRAFT, "item/handheld"))
                .key(Key.key(NAMESPACE, namespaced))
                .textures(ModelTextures.builder()
                        .addLayer(
                                ModelTexture.ofKey(Key.key(NAMESPACE, namespaced))
                        )
                        .build())
                .build();

        Item item = Item.item(Key.key(NAMESPACE, itemName),
                ItemModel.reference(model.key()));

        resourcePack.model(model);
        resourcePack.texture(texture);
        resourcePack.item(item);

        return true;
    }
}
