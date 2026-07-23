package net.corneredcube.cubesrpg.entity.client;

import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class gooberModel extends DefaultedEntityGeoModel<gooberEntity> {

    public gooberModel() {
        super(new ResourceLocation("cubesrpg", "geo/goober.geo.json"), false);
    }

    @Override
    public ResourceLocation getModelResource(gooberEntity entity) {
        return new ResourceLocation("cubesrpg", "geo/goober.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(gooberEntity entity) {
        return new ResourceLocation("cubesrpg", "textures/entity/goober.png");
    }

    @Override
    public ResourceLocation getAnimationResource(gooberEntity entity) {
        return new ResourceLocation("cubesrpg", "animations/goober.animation.json");
    }
}
