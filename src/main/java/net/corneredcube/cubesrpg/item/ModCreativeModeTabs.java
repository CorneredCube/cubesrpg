package net.corneredcube.cubesrpg.item;

import net.corneredcube.cubesrpg.block.modBlocks;
import net.minecraft.core.registries.Registries;
import net.corneredcube.cubesrpg.Cubesrpg;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> creative_mode_tabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cubesrpg.MODID);

    public static final RegistryObject<CreativeModeTab> cubesrpg_tab =
            creative_mode_tabs.register("cubesrpg_tab",() -> CreativeModeTab.builder().icon(() -> new ItemStack(modItems.copper_scythe.get()))
                    .title(Component.translatable("creativetab.cubesrpg_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(modItems.copper_scythe.get());
                        output.accept(modBlocks.blahaj_block.get());
                        output.accept(modBlocks.blahaj_cooked_block.get());
                        output.accept(modItems.boykisser_plushie_item.get());
                    })
                    .build());

    public static void register(IEventBus eventbus) {
        creative_mode_tabs.register(eventbus);
    }




}
