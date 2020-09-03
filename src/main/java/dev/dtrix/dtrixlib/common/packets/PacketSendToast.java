package dev.dtrix.dtrixlib.common.packets;

import dev.dtrix.dtrixlib.client.toasts.Toast;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSendToast implements IMessage {

    private Toast.Types type;
    private ITextComponent component;
    private long duration;

    public PacketSendToast() {}

    public PacketSendToast(Toast.Types type, ITextComponent component, long duration) {
        this.type = type;
        this.component = component;
        this.duration = duration;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = Toast.Types.byIndex(buf.readInt());
        this.component = ITextComponent.Serializer.jsonToComponent(ByteBufUtils.readUTF8String(buf));
        this.duration = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type.getIndex());
        ByteBufUtils.writeUTF8String(buf, ITextComponent.Serializer.componentToJson(this.component));
        buf.writeLong(duration);
    }

    public static class Handler implements IMessageHandler<PacketSendToast, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketSendToast message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft.getMinecraft().getToastGui().add(new Toast(message.type, message.component, message.duration));
            });
            return null;
        }
    }
}
