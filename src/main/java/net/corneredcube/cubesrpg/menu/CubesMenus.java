package net.corneredcube.cubesrpg.menu;

import net.corneredcube.cubesrpg.Cubesrpg;
import net.corneredcube.cubesrpg.dialog.DialogMenu;
import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class CubesMenus {
    public static final DeferredRegister<MenuType<?>> Menus =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Cubesrpg.MODID);
    public static final RegistryObject<MenuType<DialogMenu>> Dialog_Menu
            = Menus.register("dialog_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
        int entityId = data.readInt();
        Entity entity = inv.player.level().getEntity(entityId);
        return new DialogMenu(windowId, inv, (gooberEntity) entity);
    }));
}

