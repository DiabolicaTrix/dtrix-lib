package dev.dtrix.dtrixlib.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
public class GuiRegistry {

   private static HashMap<String, Supplier<? extends GuiScreen>> guiMap = new HashMap<>();

   public static <T extends GuiScreen> void register(String guiName, Supplier<? extends GuiScreen> gui) {
       guiMap.put(guiName, gui);
   }

   public static GuiScreen getGui(String guiName) {
        if(!guiMap.containsKey(guiName))
            return null;
       return guiMap.get(guiName).get();
   }

}
