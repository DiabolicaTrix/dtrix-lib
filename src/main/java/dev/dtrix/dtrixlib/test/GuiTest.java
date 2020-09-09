package dev.dtrix.dtrixlib.test;

import net.minecraft.client.gui.GuiScreen;

public class GuiTest extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
