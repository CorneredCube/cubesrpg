package net.corneredcube.cubesrpg.dialog;

import net.minecraft.client.gui.Font;

public class DialogOption {

    public String text;
    public int x;
    public int y;
    public Runnable onClick;

    public DialogOption(String text, int x, int y, Runnable onClick) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.onClick = onClick;
    }

    public boolean isHovered(int mouseX, int mouseY, Font font) {

        return mouseX >= x &&
                mouseX <= x + font.width(text) &&
                mouseY >= y &&
                mouseY <= y + 10;
    }
}
