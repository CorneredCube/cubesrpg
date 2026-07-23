package net.corneredcube.cubesrpg.entity.client;

import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class gooberRenderer extends GeoEntityRenderer<gooberEntity> {


    public gooberRenderer(EntityRendererProvider.Context context) {
        super(context, new gooberModel());
        this.shadowRadius = 0.4f;
    }


    }
