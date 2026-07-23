package net.corneredcube.cubesrpg.item.custom;

import net.corneredcube.cubesrpg.Cubesrpg;
import net.corneredcube.cubesrpg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class boykisser_plushie extends Item {
    public boykisser_plushie(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        int radius = 10;
        Level level = context.getLevel();
        if(!level.isClientSide()){
            double radiusSq = radius * radius;
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();
            Vec3 vec3 = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            AABB box = new AABB(
                    vec3.x - radius, vec3.y - radius, vec3.z - radius,
                    vec3.x + radius,vec3.y + radius, vec3.z + radius
            );
            boolean foundBlock = false;

            List<Entity> candidates = level.getEntities(null, box);
            for ( Entity entity : candidates) {

                if(entity instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(
                            MobEffects.GLOWING,
                            200,
                            0,
                            false,
                            false
                    ));

                    level.playSeededSound(
                            null,
                            pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.plushie_squeak.get(),
                            SoundSource.BLOCKS,
                            2.0f,
                            2.0f,
                            1
                    );
                }

            }
        }
        return InteractionResult.SUCCESS;
    }
}
