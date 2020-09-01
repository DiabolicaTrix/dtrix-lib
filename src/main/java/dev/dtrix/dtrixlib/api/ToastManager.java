package dev.dtrix.dtrixlib.api;

import dev.dtrix.dtrixlib.DTrixLib;
import dev.dtrix.dtrixlib.client.toasts.Toast;
import dev.dtrix.dtrixlib.common.packets.PacketSendToast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ToastManager {

    private static ToastManager instance;

    public void sendToast(EntityPlayer player, Toast.Types type, String message, long duration) {
        DTrixLib.network.sendTo(new PacketSendToast(type, message, duration), (EntityPlayerMP) player);
    }

    public void sendToast(EntityPlayer player, Toast.Types type, String message) {
        sendToast(player, type, message, 2000);
    }

    public static ToastManager getInstance() {
        if (instance == null)
            instance = new ToastManager();
        return instance;
    }
}
