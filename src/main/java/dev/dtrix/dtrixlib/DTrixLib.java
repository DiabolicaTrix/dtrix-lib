package dev.dtrix.dtrixlib;

import dev.dtrix.dtrixlib.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import scala.collection.immutable.Stream;

@Mod(modid = Constants.MODID, version = Constants.VERSION, name = Constants.NAME, guiFactory = Constants.GUI_FACTORY)
public class DTrixLib {

    @Mod.Instance(Constants.MODID)
    public static DTrixLib instance;

    @SidedProxy(clientSide = "dev.dtrix.dtrixlib.client.ClientProxy", serverSide = "dev.dtrix.dtrixlib.server.ServerProxy")
    public static CommonProxy proxy;

}
