package brodiebroadcasts.mayhem.item;

import brodiebroadcasts.mayhem.entity.ThornSlashEntity;
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        float f = 1.0F;
        if (!world.isClient) { // Ensure method is running on the server
            user.getItemCooldownManager().set(this, 10); // 6 second cool down

            ThornSlashEntity slashEntity = new ThornSlashEntity(world, user);
            slashEntity.setOwner(user);
            slashEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, f * 3.0F, 1.0F);
            slashEntity.setDamage(slashEntity.getDamage());
        }

        return super.use(world, user, hand);
    }
}
