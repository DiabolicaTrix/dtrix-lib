package dev.dtrix.dtrixlib.network;

import net.minecraft.nbt.NBTTagCompound;

public interface IReceiver {

    void handle(NBTTagCompound payload);

}
