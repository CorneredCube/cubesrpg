package net.corneredcube.cubesrpg.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class gooeygui extends Screen {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("Cubesrpg", "textures/gui/gooeygui.png");

    private final int xSize = 176;
    private final int ySize = 166;
    protected int leftPos; // X coordinate of GUI top-left
    protected int topPos;  // Y coordinate of GUI top-left

    protected gooeygui() {
        super(Component.literal("Gooey GUI!"));
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.xSize) / 2;
        this.topPos = (this.height - this.ySize) / 2;

        this.addRenderableWidget(Button.builder(
                        Component.literal("Click Me"),
                        button -> System.out.println("Clicked!")
                ).bounds(leftPos + 38, topPos + 70, 100, 20)
                .build());


    }

}
