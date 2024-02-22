package brodiebroadcasts.mayhem.init;

import brodiebroadcasts.mayhem.entity.ThornSlashEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static brodiebroadcasts.mayhem.Mayhem.MOD_ID;

public class ModEntities {
    public static final EntityType<ThornSlashEntity> THORN_SLASH = (FabricEntityTypeBuilder.<ThornSlashEntity>create(SpawnGroup.MISC,
            ThornSlashEntity::new).disableSaving().dimensions(EntityDimensions.changing(5.0F, 0.2F)).build());

    public static void initialize() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "thorn_slash"), THORN_SLASH);
    }
}
