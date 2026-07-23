package net.corneredcube.cubesrpg.entity.custom;

import forge.net.mca.entity.VillagerLike;
import net.corneredcube.cubesrpg.dialog.DialogManager;
import net.corneredcube.cubesrpg.entity.custom.DataManager;
import net.corneredcube.cubesrpg.dialog.DialogMenu;
import net.corneredcube.cubesrpg.dialog.DialogProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class gooberEntity extends Villager implements GeoEntity {

    private Map<UUID, Integer> relationships = new HashMap<>();
    private boolean isTrading = false;
    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<String> NPC_NAME =
            SynchedEntityData.defineId(gooberEntity.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> PERSONALITY =
            SynchedEntityData.defineId(gooberEntity.class, EntityDataSerializers.STRING);

    private static final EntityDataAccessor<String> SUB_PERSONA =
            SynchedEntityData.defineId(gooberEntity.class, EntityDataSerializers.STRING);

    public gooberEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super((EntityType<? extends Villager>) pEntityType, pLevel);

    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // You can leave this empty for now
    }
    protected void registerGoals() {
        // Movement: wander around slowly
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.10D));

        // Optional: look at players to feel alive
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 3.0F));

        // Optional: keep swimming if you happen to be in water
        this.goalSelector.addGoal(0, new RandomLookAroundGoal(this));
    }

    public void setTrading(boolean trading) {
        this.isTrading = trading;
    }

    public boolean isTrading() {
        return this.isTrading;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isTrading()) {
            this.getNavigation().stop();
            return; // prevents ANY goal from running movement logic
        }

        super.customServerAiStep();
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        //this.setSpeed(0);
        this.setTrading(true);
        if(!this.level().isClientSide){
            //0.125




        }
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, new DialogProvider(this), buf -> buf.writeInt(this.getId()));

        }

        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(NPC_NAME, setName());
        this.entityData.define(PERSONALITY, setPersonality());
        this.entityData.define(SUB_PERSONA, setSubPersona());
    }
    public String getNPCName() { return this.entityData.get(NPC_NAME); }
    public String getPersonality() { return this.entityData.get(PERSONALITY); }
    public String getSubPersona() { return this.entityData.get(SUB_PERSONA); }

    public String setPersonality() {
        DataManager dataManager = new DataManager();
        ArrayList dataline = dataManager.getData("personality");
        if (dataline == null || dataline.isEmpty()) return "Unknown";
        return (String) dataline.get((int)(Math.random() * dataline.size()));
    }
    public String setSubPersona() {
        DataManager dataManager = new DataManager();
        ArrayList dataline = dataManager.getData("sub-persona");
        if (dataline == null || dataline.isEmpty()) return "Unknown";
        return (String) dataline.get((int)(Math.random() * dataline.size()));
    }
    public String setName() {
        DataManager dataManager = new DataManager();
        ArrayList dataline = dataManager.getData("name");
        if (dataline == null || dataline.isEmpty()) return "Unknown";
        return (String) dataline.get((int)(Math.random() * dataline.size()));
    }




    /*public String getNPCName() {
        return this.name;
    }*/

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {

        tag.putString("Personality", this.entityData.get(PERSONALITY));
        tag.putString("NPCName", this.entityData.get(NPC_NAME));
        tag.putString("SubPersona", this.entityData.get(SUB_PERSONA));
        //tag.putString("Job", job);


        CompoundTag relations = new CompoundTag();
        for (Map.Entry<UUID, Integer> entry : relationships.entrySet()) {
            relations.putInt(entry.getKey().toString(), entry.getValue());
        }
        tag.put("Relationships", relations);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("NPCName"))
            this.entityData.set(NPC_NAME, tag.getString("NPCName"));
        if (tag.contains("Personality"))
            this.entityData.set(PERSONALITY, tag.getString("Personality"));
        if (tag.contains("SubPersona"))
            this.entityData.set(PERSONALITY, tag.getString("SubPersona"));

        relationships.clear();
        if (tag.contains("Relationships")) {
            CompoundTag relations = tag.getCompound("Relationships");
            for (String key : relations.getAllKeys()) {
                relationships.put(UUID.fromString(key), relations.getInt(key));
            }
        }
    }



}
