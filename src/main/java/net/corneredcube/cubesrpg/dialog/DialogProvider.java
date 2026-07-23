package net.corneredcube.cubesrpg.dialog;

import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.network.chat.Component;

import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import javax.annotation.Nullable;
import java.awt.*;

public class DialogProvider implements MenuProvider {
    private final gooberEntity entity;

    public DialogProvider(gooberEntity entity) {
        this.entity = entity;
    }

    @Override

    public Component getDisplayName() {
        return Component.literal("Dialog");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        // Create the 0-slot menu. Replace types if you’ve named them differently.
        return new DialogMenu(syncId, playerInventory, entity);
    }


}
