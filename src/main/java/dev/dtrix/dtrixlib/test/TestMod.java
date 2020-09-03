package dev.dtrix.dtrixlib.test;

import dev.dtrix.dtrixlib.client.toasts.Toast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "testmod", name = "testmod", version = "0.0.1")
@Mod.EventBusSubscriber
public class TestMod {

    public static KeyBinding[] keyBindings = new KeyBinding[1];

    @Mod.EventHandler
    public static void onPostInit(FMLPostInitializationEvent event) {
        keyBindings[0] = new KeyBinding("test", Keyboard.KEY_G, "test.category");
        ClientRegistry.registerKeyBinding(keyBindings[0]);
    }

    @SubscribeEvent
    public static void onKey(InputEvent.KeyInputEvent event) {
        if(keyBindings[0].isPressed()) {
            Minecraft.getMinecraft().getToastGui().add(new Toast(Toast.Types.ERROR, new TextComponentTranslation("test.teststring", "argument12"), 2000));
        }
    }

}
