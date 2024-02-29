package brodiebroadcasts.mayhem.entity;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.Identifier;

public class InvisibleEntityRenderer extends EntityRenderer<ThrownEntity> {
    public InvisibleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ThrownEntity entity) {
        return null;
    }
}
