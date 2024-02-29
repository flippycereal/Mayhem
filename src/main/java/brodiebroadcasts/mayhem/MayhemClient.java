package brodiebroadcasts.mayhem;

import brodiebroadcasts.mayhem.entity.InvisibleEntityRenderer;
import brodiebroadcasts.mayhem.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class MayhemClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ANGELIC_FIRE_ENTITY, InvisibleEntityRenderer::new);
    }
}
