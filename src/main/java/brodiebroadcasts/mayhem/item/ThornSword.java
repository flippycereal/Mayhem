package brodiebroadcasts.mayhem.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThornSword extends SwordItem {
    public ThornSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker.getEquippedStack(EquipmentSlot.MAINHAND).isOf(this)) { // Ensure it is running on server side and the weapon is in MainHand
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,
                    100, 1, true, false, false)); // Adds the status effect poison to the entity hit for 5 seconds
        }
        return super.postHit(stack, target, attacker);
    }
}
