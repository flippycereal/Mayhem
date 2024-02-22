package brodiebroadcasts.mayhem.init;

import brodiebroadcasts.mayhem.entity.ThornSlashEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ModEntities {
    EntityType<ThornSlashEntity> THORN_SLASH = (FabricEntityTypeBuilder.<ThornSlashEntity>create(SpawnGroup.MISC,
            ThornSlashEntity::new).disableSaving().dimensions(EntityDimensions.changing(5.0F, 0.2F)).build());
}
