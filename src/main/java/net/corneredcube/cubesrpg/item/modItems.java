package net.corneredcube.cubesrpg.item;

import net.corneredcube.cubesrpg.Cubesrpg;
import net.corneredcube.cubesrpg.item.custom.boykisser_plushie;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.corneredcube.cubesrpg.block.modBlocks.blahaj_cooked_block;

public class modItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cubesrpg.MODID);

    public static final RegistryObject<Item> copper_scythe = ITEMS.register("copper_scythe", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> boykisser_plushie_item = ITEMS.register("boykisser_plushie", () -> new boykisser_plushie(new Item.Properties()));
    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
