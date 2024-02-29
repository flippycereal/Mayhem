package brodiebroadcasts.mayhem.item;

import brodiebroadcasts.mayhem.entity.AngelicFireEntity;
import brodiebroadcasts.mayhem.init.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class AngelicBlade extends SwordItem {
    public AngelicBlade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private static Random random = new Random();

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker.getEquippedStack(EquipmentSlot.MAINHAND).isOf(this)) { // Ensure it is running on server side and the weapon is in MainHand
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,
                    100, 1, true, false, false));// Adds glowing for 5 seconds
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING,
                    100, 0, true, false, false)); // Adds slow falling for 5 seconds
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (EnchantmentHelper.getEquipmentLevel(ModEnchantments.ANGELIC_DASH, user) <= 0) {
        } else {
            user.getWorld().addParticle(ParticleTypes.EXPLOSION, true,
                    user.getX() + random.nextFloat(), user.getY() + random.nextFloat(), user.getZ() + random.nextFloat(),
                    0, 0.1, 0);
            if (!world.isClient()) {
                user.getItemCooldownManager().set(this, 10); // half second cool-down
                for (int i = 0; i < 50; i++) {
                    user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_FIREWORK_ROCKET_TWINKLE, SoundCategory.PLAYERS, 1.0F, 1.0f);
                }
            }
            Vec3d vec3d = user.getRotationVector();
            Vec3d vec3d2 = user.getVelocity();
            user.setVelocity(vec3d2.add(vec3d.x * 5.25 - vec3d2.x * 0.5, vec3d.y * 3 - vec3d2.y, vec3d.z * 5.25 - vec3d2.z * 0.5));
            user.velocityModified = true;
            user.limitFallDistance();
            user.damage(user.getDamageSources().generic(), 2);
        }

        if(EnchantmentHelper.getEquipmentLevel(ModEnchantments.ANGELIC_FIRE, user) <= 0) {
        } else {
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL,
                    0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                AngelicFireEntity angelicFireEntity = new AngelicFireEntity(world, user);
                angelicFireEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
                world.spawnEntity(angelicFireEntity);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
