package net.corneredcube.cubesrpg.dialog;

import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.corneredcube.cubesrpg.menu.CubesMenus;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class DialogMenu extends AbstractContainerMenu {

    private final gooberEntity entity;
    public DialogMenu(int id, Inventory inventory, gooberEntity entity) {
        super(CubesMenus.Dialog_Menu.get(), id);
        this.entity = entity;
    }
    @Override
    public boolean stillValid(Player player) {
        return entity.isAlive() && player.distanceToSqr(entity) <= 8.0F;
    }
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        if (!player.level().isClientSide) {
            if (this.entity != null) {
                this.entity.setTrading(false);
            }
        }
    }

    public gooberEntity getEntity() {
        return this.entity;
    }

}
