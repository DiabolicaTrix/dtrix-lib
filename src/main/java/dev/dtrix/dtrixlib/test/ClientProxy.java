package dev.dtrix.dtrixlib.test;

import dev.dtrix.dtrixlib.client.GuiRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        GuiRegistry.register("test", GuiTest::new);
    }

}
