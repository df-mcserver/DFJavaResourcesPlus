package uk.co.nikodem.DFJavaResourcesPlus.AssetSearchers;

import net.kyori.adventure.key.Key;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Vector3Float;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.item.Item;
import team.unnamed.creative.item.ItemModel;
import team.unnamed.creative.model.ItemTransform;
import team.unnamed.creative.model.Model;
import team.unnamed.creative.model.ModelTexture;
import team.unnamed.creative.model.ModelTextures;
import team.unnamed.creative.texture.Texture;
import uk.co.nikodem.DFJavaResourcesPlus.AssetSearcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static uk.co.nikodem.DFJavaResourcesPlus.Main.MINECRAFT;
import static uk.co.nikodem.DFJavaResourcesPlus.Main.NAMESPACE;

public class ComicallyLargeSearcher implements AssetSearcher {
    @Override
    public String getAssetTypeName() {
        return "comically large";
    }

    @Override
    public void run(ResourcePack resourcePack) {
        Map<ItemTransform.Type, ItemTransform> displays = new HashMap<>();

        displays.put(ItemTransform.Type.FIRSTPERSON_RIGHTHAND, ItemTransform.transform(
                new Vector3Float(0F, -90F, 25F),
                new Vector3Float(1.13F, 3.2F, 1.13F),
                new Vector3Float(4F, 4F, 4F)
        ));

        displays.put(ItemTransform.Type.THIRDPERSON_RIGHTHAND, ItemTransform.transform(
                new Vector3Float(0F, -90F, 55F),
                new Vector3Float(0F, 24F, 0F),
                new Vector3Float(4F, 4F, 4F)
        ));

        displays.put(ItemTransform.Type.GUI, ItemTransform.transform(
                new Vector3Float(0F, 0F, 0F),
                new Vector3Float(-3F, -3F, 0F),
                new Vector3Float(4F, 4F, 4F)
        ));

        displays.put(ItemTransform.Type.GROUND, ItemTransform.transform(
                new Vector3Float(0F, 0F, 0F),
                new Vector3Float(0F, 0F, 0F),
                new Vector3Float(4F, 4F, 4F)
        ));

        Texture texture = Texture.texture()
                .key(Key.key(NAMESPACE, "item/comically_large_shovel.png"))
                .data(Writable.file(new File("assets/etc/comically_large_shovel.png")))
                .build();

        Model model = Model.model()
                .parent(Key.key(MINECRAFT, "item/handheld"))
                .key(Key.key(NAMESPACE, "item/comically_large_shovel"))
                .display(displays)
                .textures(ModelTextures.builder()
                        .layers(
                                // without .png extension
                                ModelTexture.ofKey(Key.key(NAMESPACE, "item/comically_large_shovel"))
                        )
                        .build()
                )
                .ambientOcclusion(true)
                .guiLight(Model.GuiLight.FRONT).build();

        Item item = Item.item(Key.key(NAMESPACE, "comically_large_shovel"),
                ItemModel.reference(model.key()));

        resourcePack.texture(texture);
        resourcePack.model(model);
        resourcePack.item(item);
    }
}
