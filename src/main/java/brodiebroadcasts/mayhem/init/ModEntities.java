package brodiebroadcasts.mayhem.init;

import brodiebroadcasts.mayhem.entity.AngelicFireEntity;
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

import static brodiebroadcasts.mayhem.Mayhem.MOD_ID;

public class ModEntities {
    public static final EntityType<AngelicFireEntity> ANGELIC_FIRE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "angelic_fire"),
            FabricEntityTypeBuilder.<AngelicFireEntity>create(SpawnGroup.MISC, AngelicFireEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 1)).build());


    public static void initialize() {

    }
}