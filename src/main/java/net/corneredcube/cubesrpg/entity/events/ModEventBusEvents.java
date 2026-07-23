package net.corneredcube.cubesrpg.entity.events;

import net.corneredcube.cubesrpg.Cubesrpg;
import net.corneredcube.cubesrpg.entity.ModEntities;
import net.corneredcube.cubesrpg.entity.client.gooberModel;
import net.corneredcube.cubesrpg.entity.client.ModModelLayers;
import net.corneredcube.cubesrpg.entity.client.gooberRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cubesrpg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.goober.get(),
                gooberRenderer::new
        );
    }
}
