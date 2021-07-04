package dev.dtrix.dtrixlib.client.interaction;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class InteractionMenu extends GuiScreen {

    private final List<IInteractionAction> actions;
    private final KeyBinding keyBinding;
    private int innerRadius = 20;
    private int outerRadius = 80;
    private int centerX;
    private int centerY;
    private final int segments;
    private final float degreesPerSegment;
    private Entity target;

    private int active;

    public InteractionMenu(List<IInteractionAction> actions, KeyBinding keyBinding) {
        this.actions = actions;
        this.keyBinding = keyBinding;
        segments = actions.size();
        degreesPerSegment = 360F / segments;
    }

    public InteractionMenu(List<IInteractionAction> actions) {
        this(actions, null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        bufferbuilder.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);

        active = getActiveSector(mouseX, mouseY);

        for (int i = 0; i < segments; i++) {
            double angle = Math.toRadians(degreesPerSegment * i);
            int resolution = 20;

            for (int j = 0; j <= resolution; j++) {
                double offset = Math.toRadians((float) j / resolution * degreesPerSegment);
                double x = centerX + outerRadius * Math.cos(angle + offset);
                double y = centerY + outerRadius * Math.sin(angle + offset);
                bufferbuilder.pos(x, y, 0).color(active == i ? 0.8F : 1.0F, active == i ? 0.094F : 1.0F, active == i ? 0.094F : 1.0F, 1.0F).endVertex();
                x = centerX + innerRadius * Math.cos(angle + offset);
                y = centerY + innerRadius * Math.sin(angle + offset);
                bufferbuilder.pos(x, y, 0).color(active == i ? 0.8F : 1.0F, active == i ? 0.094F : 1.0F, active == i ? 0.094F : 1.0F, 1.0F).endVertex();
            }
        }
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        //Label on hover and icon
        for(int i = 0; i < segments; i++) {
            double angle = Math.toRadians(degreesPerSegment * i);
            double x = centerX + (innerRadius + ((float) outerRadius - innerRadius)/2) * Math.cos(angle + Math.toRadians(degreesPerSegment / 2)) - 16;
            double y = centerY + (innerRadius + ((float) outerRadius - innerRadius)/2) * Math.sin(angle + Math.toRadians(degreesPerSegment / 2)) - 16;
            this.mc.getTextureManager().bindTexture(actions.get(i).getIcon());
            this.drawTexturedModalRect((float) x, (float) y, 0, 0, 32, 32);
            if(i == active) {
                x = centerX + (outerRadius + 5) * Math.cos(angle + Math.toRadians(degreesPerSegment / 2));
                y = centerY + (outerRadius + 5) * Math.sin(angle + Math.toRadians(degreesPerSegment / 2));
                if (!(x - centerX > 0)) {
                    x = x - fontRenderer.getStringWidth(actions.get(active).getName());
                }
                this.drawString(mc.fontRenderer, actions.get(active).getName(), (int) x, (int) y, 0xFFFFFF);
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    private int getActiveSector(int mouseX, int mouseY) {
        double angle = Math.toDegrees(Math.atan2(mouseY - centerY, mouseX - centerX));
        angle = angle < 0 ? 360 + angle : angle;
        return (int) Math.floor(angle / degreesPerSegment);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.centerX = this.width / 2;
        this.centerY = this.height / 2;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if(keyBinding != null && !GameSettings.isKeyDown(this.keyBinding)) {
            mc.displayGuiScreen(null);
            actions.get(active).execute(Minecraft.getMinecraft().player);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
