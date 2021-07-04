package dev.dtrix.dtrixlib.client.interaction;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public interface IInteractionAction {

    void execute(EntityPlayer target);

    String getName();

    ResourceLocation getIcon();

}
