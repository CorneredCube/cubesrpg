package net.corneredcube.cubesrpg.dialog;

import net.corneredcube.cubesrpg.entity.custom.gooberEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;

import net.minecraft.client.gui.narration.NarrationElementOutput;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import java.awt.*;
import java.util.List;
import java.util.ArrayList;




@OnlyIn(Dist.CLIENT)
public class DialogScreen extends AbstractContainerScreen<DialogMenu> {

    private final List<DialogOption> options = new ArrayList<>();

    enum DialoguePage{
        greeting,
        chat1,
        interact1,
        joke,
    }
    private DialoguePage currentPage = DialoguePage.greeting;
    DialogManager dialogManager = new DialogManager();
    public ArrayList dialogLines = dialogManager.getDialog("greeting");
    public ArrayList jokeLines = dialogManager.getJoke("animal");
    int index = (int)( Math.random() * dialogLines.size());
    int index2 = (int)( Math.random() * jokeLines.size());
    //the screen itself
    public DialogScreen(DialogMenu menu, Inventory inventory, Component title) {
        super(menu,inventory, title);
        this.imageWidth = 300;
        this.imageHeight = 80;
    }
    //dialogues
    private void setDialogue(String a){

        dialogLines = dialogManager.getDialog(a);
        index = (int)( Math.random() * dialogLines.size());
    }
    //joker
    private void setJoke(String a){

        jokeLines = dialogManager.getJoke(a);
        index2 = (int)( Math.random() * jokeLines.size());
    }
    //main init bruv
    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        options.clear();
        // move the GUI just above the player HUD
        int paddingFromHUD = 10; // space between GUI and hotbar
        this.topPos = this.height - this.imageHeight - 20 - paddingFromHUD;
        this.leftPos = (this.width - this.imageWidth) / 2;
        int buttonX = (leftPos + imageWidth - 70 ) + 10;
        switch (currentPage) {
            case greeting:
                this.addRenderableWidget(

                    new button((buttonX
                            //this.leftPos + 255
                            ), (this.topPos - 70), "Chat", () -> {
                        currentPage = DialoguePage.chat1;
                        this.init();
                    }));
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 60), "Interact", () -> {
                            currentPage = DialoguePage.interact1;
                            this.init();
                        }));
                break;

            case chat1:
                setDialogue("chatTransition");
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 75), "Story", () -> {
                            //currentPage = DialoguePage.chat1;
                            this.init();
                        }));
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 60), "Joke", () -> {
                            currentPage = DialoguePage.joke;
                            this.init();
                        }));
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 45), "Gossip", () -> {
                            //currentPage = DialoguePage.chat1;
                            this.init();
                        }));
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 30), "Flirt", () -> {
                            currentPage = DialoguePage.greeting;
                            setDialogue("greeting");
                            this.init();
                        }));
                this.addRenderableWidget(

                        new button(buttonX, (this.topPos - 15), "Nevermind", () -> {
                            currentPage = DialoguePage.greeting;
                            setDialogue("greeting");
                            this.init();
                        }));

                break;
            case interact1:
                setDialogue("interactTransition");
                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 70), "Trade", () -> {
                            currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));
                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 55), "Gift", () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));

                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 45), "Hug", () -> {
                            //currentPage = DialoguePage.greeting;
                           // setDialogue("greeting");
                            this.init();
                        }));

                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 30), "Kiss", () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));

                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 15), "Nevermind", () -> {
                            currentPage = DialoguePage.greeting;
                            setDialogue("greeting");
                            this.init();
                        }));
            break;
            case joke:

                setJoke("animal");
                setDialogue("blank");
                this.addRenderableWidget(
                        new button(buttonX, (this.topPos - 15), "Nevermind", () -> {
                            currentPage = DialoguePage.greeting;
                            setDialogue("greeting");
                            this.init();
                        }));
                this.addRenderableWidget(
                        new button(this.leftPos, (this.topPos +10), (String)(jokeLines.get((int)(index2))), () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));
                setJoke("monster");
                this.addRenderableWidget(
                        new button(this.leftPos, (this.topPos +25), (String)(jokeLines.get((int)(index2))), () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));
                setJoke("dimension");
                this.addRenderableWidget(
                        new button(this.leftPos, (this.topPos +40), (String)(jokeLines.get((int)(index2))), () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));
                setJoke("biome");
                this.addRenderableWidget(
                        new button(this.leftPos, (this.topPos +55), (String)(jokeLines.get((int)(index2))), () -> {
                            //currentPage = DialoguePage.greeting;
                            //setDialogue("greeting");
                            this.init();
                        }));
        }

    }

    private void createButton(String text,int x, int y, Runnable onClick) {
        DialogOption option = new DialogOption(text,x,y, onClick);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        // draw background texture here
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0x88000000);
    }
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Position relative to the GUI box

    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        return super.mouseClicked(mouseX, mouseY, button);
    }


    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics); // darken outside area (optional)
        super.render(graphics, mouseX, mouseY, partialTicks);
        String sentence = (String) dialogLines.get(index);
        FormattedText text = Component.literal(sentence);

        List<FormattedCharSequence> lines = (List) this.font.split(text, 200);
        String name = menu.getEntity().getNPCName();

        int boxLeft = leftPos;
        int boxRight = leftPos + imageWidth / 5;
        int boxTop = topPos - 15;
        int boxBottom = topPos;
        int padding = 10;
        int yOffset = 0;
        graphics.drawString(
                this.font,
                name,
                Math.max(0, (boxLeft + boxRight - font.width(name)) / 2),
                topPos-10,
                0xFFFFFF,
                false
        );
        graphics.fill(
                boxLeft ,  // left edge  = right-aligned with box
                boxTop,            // top edge   = above box with 5px gap
                boxRight,            // right edge = right edge of box
                boxBottom,                      // bottom edge = 5px above box
                0x80000000
        );
        for (FormattedCharSequence line : lines) {
            graphics.drawString(
                    this.font,
                    line,
                    leftPos + padding,
                    topPos + padding + 1 * (font.lineHeight + 2) + yOffset,
                    0xFFFFFF,
                    false
            );
            yOffset += 12;
        }

        // options box

        int boxSize = 70;
        graphics.fill(
                leftPos + imageWidth - boxSize,  // left edge  = right-aligned with box
                topPos - boxSize - 5,            // top edge   = above box with 5px gap
                leftPos + imageWidth,            // right edge = right edge of box
                topPos - 5,                      // bottom edge = 5px above box
                0x80000000
        );

    }

    private class button extends AbstractWidget {

        private final Runnable onClick;

        button(int x, int y, String text, Runnable onClick) {
            super(x, y, 50, 10, Component.literal(text));
            this.onClick = onClick;
        }

        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
            int color = isMouseOver(mouseX, mouseY) ? 0xFFFF00 : 0xFFFFFF;
            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX(), getY(), color, false);
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
            onClick.run();
        }
        @Override
        protected void updateWidgetNarration(NarrationElementOutput narration) {
            this.defaultButtonNarrationText(narration);
        }
    }



}

