package brodiebroadcasts.mayhem.entity;

import brodiebroadcasts.mayhem.init.ModEntities;
import brodiebroadcasts.mayhem.init.ModItems;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngelicFireEntity extends ThrownItemEntity {
    public static final int BURN_RADIUS = 3;


    public AngelicFireEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ANGELIC_BLADE;
    }

    public AngelicFireEntity(World world, LivingEntity owner) {
        super(ModEntities.ANGELIC_FIRE_ENTITY, owner, world);
        this.setOwner(owner);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            BlockPos blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.getWorld().setBlockState(blockPos, AbstractFireBlock.getState(this.getWorld(), blockPos));
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient) {
            return;
        }
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        int i = entity.getFireTicks();
        entity.setOnFireFor(5);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.setVelocity(this.getVelocity().multiply(0.9, 0.9, 0.9));
    }
}
