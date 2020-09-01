package dev.dtrix.dtrixlib.client;

import dev.dtrix.dtrixlib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.ArrayList;
import java.util.Set;

public class GuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiLibConfig(parentScreen);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public static class GuiLibConfig extends GuiConfig {

        public GuiLibConfig(GuiScreen parentScreen) {
            super(parentScreen, new ArrayList<>(), Constants.MODID, false, false, I18n.format("gui.configuration.title"));
        }



    }

}
