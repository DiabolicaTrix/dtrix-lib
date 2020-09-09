package dev.dtrix.dtrixlib.common.packets;

import dev.dtrix.dtrixlib.client.GuiRegistry;
import dev.dtrix.dtrixlib.network.IReceiver;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class PacketClientAction implements IMessage {
    private static AtomicInteger previousActionID = new AtomicInteger();

    @Setter
    private NBTTagCompound payload;
    private int actionID;

    public PacketClientAction() {
        actionID = previousActionID.getAndUpdate(previous -> previous > 32765 ? 0 : previous + 1);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.actionID = buf.readInt();
        this.payload = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(actionID);
        ByteBufUtils.writeTag(buf, this.payload);
    }

    public static class Handler implements IMessageHandler<PacketClientAction, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketClientAction message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
               if(message.getPayload().hasKey("gui")) {
                   GuiScreen gui = GuiRegistry.getGui(message.getPayload().getString("gui"));
                   if(gui instanceof IReceiver)
                       ((IReceiver) gui).handle(message.getPayload());
                   if(Minecraft.getMinecraft().currentScreen != gui)
                       Minecraft.getMinecraft().displayGuiScreen(gui);
               }
            });
            return null;
        }
    }

}
