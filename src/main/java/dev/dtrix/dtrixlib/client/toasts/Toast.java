package dev.dtrix.dtrixlib.client.toasts;

import dev.dtrix.dtrixlib.Constants;
import dev.dtrix.dtrixlib.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiModList;
import net.voxelindustry.brokkgui.paint.Color;
import net.voxelindustry.brokkgui.util.StringCountUtils;
import org.lwjgl.opengl.GL11;

public class Toast implements IToast {

    private IToast.Visibility visibility = Visibility.SHOW;
    private long duration;
    private Types type;
    private String message;

    public Toast(Types type, String message, long duration) {
        this.duration = duration;
        this.type = type;
        this.message = I18n.format(message);
    }

    @Override
    public Visibility draw(GuiToast toastGui, long delta) {
        if(delta >= this.duration) {
            this.hide();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Gui.drawRect(0, 3, 160, 35, Color.fromHex("FFFFFF").toRGBAInt());
        Gui.drawRect(0, 3, 32, 35, type.getColor());

        String str = StringUtils.abbreviateSplitString(toastGui.getMinecraft().fontRenderer, this.message, 122, 3);
        toastGui.getMinecraft().fontRenderer.drawSplitString(str, 35, 6, 122,0x000000);

        this.type.draw(toastGui, 0, 3);
        return this.visibility;
    }

    public void hide() { this.visibility = Visibility.HIDE; }

    public enum Types {
        INFO("517AFF", 0),
        SUCCESS("00CD21", 1),
        ERROR("FF1131", 2),
        WARNING("FF7247",3);

        private static final ResourceLocation ICON_TEXTURE = new ResourceLocation(Constants.MODID, "textures/gui/icons/icons.png");
        private String color;
        private int index;

        Types(String color, int index) {
            this.color = color;
            this.index = index;
        }

        public int getColor() {
            return Color.fromHex(color).toRGBAInt();
        }

        public void draw(Gui gui, int x, int y) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(ICON_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            Gui.drawScaledCustomSizeModalRect(x, y, this.index%2 * 16, this.index/2 * 16, 16, 16, 32, 32, 32, 32);
            GlStateManager.enableBlend();
        }

        public int getIndex() {
            return index;
        }

        public static Types byIndex(int index) {
            for (Types value : values()) {
                if(value.getIndex() == index)
                    return value;
            }
            return INFO;
        }
    }

}

