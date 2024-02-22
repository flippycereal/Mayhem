package brodiebroadcasts.mayhem.entity;

import brodiebroadcasts.mayhem.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Iterator;

public class ThornSlashEntity extends PersistentProjectileEntity{
    public int ticksUntilRemove = 5;
    public ThornSlashEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThornSlashEntity(World world, PlayerEntity user) {
        super(ModEntities.THORN_SLASH, user, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();

        for(float x = -3.0F; x <= 3.0F; x = (float)((double)x + 0.1)) {
            this.getWorld().addParticle(ParticleTypes.FLAME,
                    this.getX() + (double)x * Math.cos((double)this.getYaw()), this.getY(), this.getZ() + (double)x * Math.sin((double)this.getYaw()),
                    this.getVelocity().getX(), this.getVelocity().getY(), this.getVelocity().getZ());
        }

        if (this.inGround || this.age > 20) {
            for(int i = 0; i < 50; ++i) {
                this.getWorld().addParticle(ParticleTypes.ASH,
                        this.getX() + this.random.nextGaussian() * 2.0 * Math.cos((double)this.getYaw()), this.getY(), this.getZ() + this.random.nextGaussian() * 2.0 * Math.sin((double)this.getYaw()),
                        this.random.nextGaussian() / 10.0, (double)(this.random.nextGaussian() / 2.0F), this.random.nextGaussian() / 10.0);
            }
            --this.ticksUntilRemove;
        }

        if (this.ticksUntilRemove <= 0) {
            this.discard();
        }

        if (!this.getWorld().isClient) {
            Iterator var6 = this.getWorld().getEntitiesByClass(LivingEntity.class, this.getBoundingBox(), (livingEntityx) -> {
                return this.getOwner() != livingEntityx;
            }).iterator();

            while(var6.hasNext()) {
                LivingEntity livingEntity = (LivingEntity)var6.next();
                livingEntity.damage(new DamageSource((RegistryEntry<DamageType>) DamageTypes.GENERIC), 12.0F);
            }
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_BAMBOO_BREAK;
    }


}
