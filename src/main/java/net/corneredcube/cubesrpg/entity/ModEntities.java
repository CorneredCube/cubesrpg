package net.corneredcube.cubesrpg.entity;

import net.corneredcube.cubesrpg.Cubesrpg;
import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Cubesrpg.MODID);

    public static final RegistryObject<EntityType<gooberEntity>> goober =
            ENTITIES.register("goober",
                    () -> EntityType.Builder.of(gooberEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f) // width, height
                            .build("goober"));

    public static void register(IEventBus eventbus) {
        ENTITIES.register(eventbus);
    }
}
