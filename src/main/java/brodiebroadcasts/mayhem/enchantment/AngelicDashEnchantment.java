package brodiebroadcasts.mayhem.enchantment;

import brodiebroadcasts.mayhem.init.ModEnchantments;
import brodiebroadcasts.mayhem.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class AngelicDashEnchantment extends Enchantment {

    public AngelicDashEnchantment(Rarity rarity, EquipmentSlot... slotTypes) {
        super(rarity, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(ModItems.ANGELIC_BLADE);
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != ModEnchantments.ANGELIC_FIRE;
    }
}
