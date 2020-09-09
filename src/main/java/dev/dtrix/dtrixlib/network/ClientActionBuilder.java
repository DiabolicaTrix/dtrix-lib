package dev.dtrix.dtrixlib.network;

import dev.dtrix.dtrixlib.DTrixLib;
import dev.dtrix.dtrixlib.common.packets.PacketClientAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;


public class ClientActionBuilder {

    private String key;
    private NBTTagCompound payload;
    private PacketClientAction packet;
    private EntityPlayer player;

    public ClientActionBuilder(String key) {
        this.key = key;
        this.payload = new NBTTagCompound();
        this.packet = new PacketClientAction();
    }

    public ClientActionBuilder withGui(String gui) {
        payload.setString("gui", gui);
        return this;
    }

    public ClientActionBuilder withString(String string) {
        payload.setString("string", string);
        return this;
    }

    public ClientActionBuilder withInt(String string) {
        payload.setString("int", string);
        return this;
    }

    public ClientActionBuilder withLong(String string) {
        payload.setString("long", string);
        return this;
    }

    public ClientActionBuilder withFloat(String string) {
        payload.setString("float", string);
        return this;
    }

    public ClientActionBuilder toPlayer(EntityPlayer player) {
        this.player = player;
        return this;
    }

    public ClientActionBuilder then(ICallback callback) {
        ActionManager.getInstance().addCallback(this.packet.getActionID(), callback);
        return this;
    }

    public void send() {
        this.packet.setPayload(payload);
        DTrixLib.network.sendTo(this.packet, (EntityPlayerMP) this.player);
    }

}
