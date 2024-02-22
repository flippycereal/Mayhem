package brodiebroadcasts.mayhem.init;

import brodiebroadcasts.mayhem.item.ThornSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static brodiebroadcasts.mayhem.Mayhem.MOD_ID;

public class ModItems {

    public static final Item THORN_SWORD = register(
            new ThornSword(ToolMaterials.NETHERITE, 1, 1, new FabricItemSettings()), "thorn_sword");



    public static <T extends Item> T register(T item, String id){
        Identifier itemID = new Identifier(MOD_ID, id);
        T registeredItem = Registry.register(Registries.ITEM, itemID, item);

        return registeredItem;
    }

    public static final ItemGroup MAYHEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.mayhem"))
            .build();
    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "mayhem_group"), MAYHEM_GROUP);
        var groupRegistryKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(MOD_ID, "mayhem_group"));
        ItemGroupEvents.modifyEntriesEvent(groupRegistryKey).register(itemGroup -> {
            itemGroup.add(ModItems.THORN_SWORD);
        });
    }
}
