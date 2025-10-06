package uk.co.nikodem.DFJavaResourcesPlus.ItemBuilders;

import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.equipment.Equipment;
import team.unnamed.creative.equipment.EquipmentLayer;
import team.unnamed.creative.texture.Texture;

import static uk.co.nikodem.DFJavaResourcesPlus.Main.NAMESPACE;

public class DFArmourTexture {
    public static boolean createArmourTexture(ResourcePack resourcePack, @Subst("example_armour") String armourName1, Writable file1, @Subst("example_armour") String armourName2, Writable file2) {
        Texture texture1 = Texture.texture()
                .key(Key.key(NAMESPACE, "entity/equipment/humanoid/"+armourName1+".png"))
                .data(file1)
                .build();

        Texture texture2 = Texture.texture()
                .key(Key.key(NAMESPACE, "entity/equipment/humanoid_leggings/"+armourName2+".png"))
                .data(file2)
                .build();

        Equipment equipment = Equipment.equipment()
                .key(Key.key(NAMESPACE, armourName1))
                .addHumanoidLayer(
                        EquipmentLayer.layer(Key.key("dfjr", armourName1))
                )
                .addHumanoidLeggingsLayer(
                        EquipmentLayer.layer(Key.key("dfjr", armourName2))
                )
                .build();

        resourcePack.texture(texture1);
        resourcePack.texture(texture2);
        resourcePack.equipment(equipment);

        return true;
    }
}
