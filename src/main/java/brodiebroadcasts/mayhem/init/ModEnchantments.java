package brodiebroadcasts.mayhem.init;

import brodiebroadcasts.mayhem.enchantment.AngelicDashEnchantment;
import brodiebroadcasts.mayhem.enchantment.AngelicFireEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static brodiebroadcasts.mayhem.Mayhem.MOD_ID;

public class ModEnchantments {

    public static Enchantment ANGELIC_DASH = new AngelicDashEnchantment
            (Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND);
    public static Enchantment ANGELIC_FIRE = new AngelicFireEnchantment
            (Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND);


    public static void initialize() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "angelic_dash"), ANGELIC_DASH);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "angelic_fire"), ANGELIC_FIRE);
    }
}
