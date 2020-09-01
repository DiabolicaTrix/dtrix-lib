package dev.dtrix.dtrixlib;

import dev.dtrix.dtrixlib.common.CommonProxy;
import dev.dtrix.dtrixlib.common.packets.PacketSendToast;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import scala.collection.immutable.Stream;

@Mod(modid = Constants.MODID, version = Constants.VERSION, name = Constants.NAME, guiFactory = Constants.GUI_FACTORY)
public class DTrixLib {

    @Mod.Instance(Constants.MODID)
    public static DTrixLib instance;

    @SidedProxy(clientSide = "dev.dtrix.dtrixlib.client.ClientProxy", serverSide = "dev.dtrix.dtrixlib.server.ServerProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
        network.registerMessage(PacketSendToast.Handler.class, PacketSendToast.class, 0, Side.CLIENT);

    }

}
