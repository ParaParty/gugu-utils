package com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla;

import codechicken.lib.math.MathHelper;
import codechicken.lib.render.state.GlStateTracker;
import codechicken.lib.vec.Vector3;
import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.handler.ClientEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TESREnergyOutputPort extends TileEntitySpecialRenderer<TileEnergyOutputPort> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(GuGuUtils.MODID, "textures/particles/laser.png");
    public static final ResourceLocation TEXTURE_STILL = new ResourceLocation(GuGuUtils.MODID, "textures/particles/laser_still.png");
    public final DecimalFormat ENERGY_FORMATTER = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US));

    private String formatEnergy(float energy) {
        if (energy >= 1000000000) {
            return ENERGY_FORMATTER.format(energy / 1000000000) + "G";
        } else if (energy >= 1000000) {
            return ENERGY_FORMATTER.format(energy / 1000000) + "M";
        } else if (energy >= 1000) {
            return ENERGY_FORMATTER.format(energy / 1000) + "K";
        } else {
            return String.valueOf(energy);
        }
    }

    @Override
    public void render(TileEnergyOutputPort te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {


        //输出激光
        if (te.getState() != OutputPortState.CONNECTED && te.getState() != OutputPortState.OUTPUTING)
            return;
        if (te.getConnectedTE() == null)
            return;
        Vec3d startPos = new Vec3d(x, y, z);
        Vec3d destPos = new Vec3d(te.getConnectedTE().getPos()).subtract(TileEntityRendererDispatcher.staticPlayerX, TileEntityRendererDispatcher.staticPlayerY, TileEntityRendererDispatcher.staticPlayerZ);
        EnumFacing facing = te.getFacing();
        Vec3d direction = destPos.subtract(startPos);

        if (startPos.distanceTo(destPos) <= 1) {
            return;
        }

        for (EnumFacing dir : EnumFacing.VALUES) {
            if (dir == facing || dir == EnumFacing.DOWN || dir == EnumFacing.UP)
                continue;
            //能量
            if (te.getEnergyProducing() <= 0) {
                continue;
            }

            String text = formatEnergy(te.getEnergyProducing()) + " RF/t";
            int color = 0xFFD8D8D8;
            if (te.getState() != OutputPortState.OUTPUTING) {
                color = 0xFF8D8D8D;
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);
            GlStateManager.glNormal3f(0F, 1F, 0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            GlStateManager.rotate(180F, 0F, 0F, 1F);
            GlStateManager.rotate(dir.getHorizontalAngle() + 180F, 0F, 1F, 0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            setLightmapDisabled(true);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.depthMask(true);
            GlStateManager.enableAlpha();
            FontRenderer font = getFontRenderer();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0.5, 0.5, -0.01);
            int len = font.getStringWidth(text);
            double scale = 0.012;
            double w = len * scale;
            if (w > 1) {
                scale /= w;
                w = 1D;
            }
            if (w > 0.45) {
                scale *= 0.45;
            }
            GlStateManager.scale(scale, scale, 1D);
            font.drawString(text, -(float) len / 2, -(float) font.FONT_HEIGHT / 2, color, false);
            GlStateManager.popMatrix();

            setLightmapDisabled(false);
            GlStateManager.enableLighting();
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.popMatrix();
        }

        if (new Vec3d(facing.getDirectionVec()).distanceTo(direction.normalize()) > 0.01) {
            return;
        }
        double dirLength = direction.lengthSquared();
        direction = direction.add(new Vec3d(facing.getOpposite().getDirectionVec()));

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateTracker.pushState();
        GlStateManager.depthMask(false);
        GlStateManager.glTexParameterf(3553, 10242, 10497.0F);
        GlStateManager.glTexParameterf(3553, 10243, 10497.0F);
        GlStateManager.disableCull();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0F);

        if (te.getState() == OutputPortState.OUTPUTING)
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        else
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_STILL);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        final float[] offsets = {0.25f, -0.25f};
        Vec3d basePlane = getBasePlane(facing);
        double scale = 0.15;
        if (te.getState() == OutputPortState.OUTPUTING)
            scale = 0.2;
        for (float offsetX : offsets) {
            for (float offsetY : offsets) {
                renderLazer(buffer, partialTicks,
                        startPos.add(addVector2d(basePlane, offsetX, offsetY)),
                        direction, scale);
            }
        }

        renderLazer(buffer, partialTicks,
                startPos.add(basePlane),
                direction, scale * 4);

        tessellator.draw();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateTracker.popState();
    }

    private Vec3d getBasePlane(EnumFacing facing) {
        return new Vec3d(facing.getDirectionVec()).add(1, 1, 1).scale(0.5);
    }

    private Vec3d addVector2d(Vec3d vec3d, float x, float y) {
        if (vec3d.x == 0 || vec3d.x == 1) {
            return vec3d.add(0, x, y);
        }
        if (vec3d.y == 0 || vec3d.y == 1) {
            return vec3d.add(x, 0, y);
        }
        if (vec3d.z == 0 || vec3d.z == 1) {
            return vec3d.add(x, y, 0);
        }
        return vec3d;
    }


    public void renderLazer(BufferBuilder buffer, float partialTicks, Vec3d start, Vec3d dest, double scale) {

        Vector3 source = new Vector3(start);
        Vector3 target = new Vector3(dest).add(source);
        Vector3 dirVec = source.copy().subtract(target).normalize();
        Vector3 planeA = dirVec.copy().perpendicular().normalize();
        Vector3 planeB = dirVec.copy().crossProduct(planeA);
        Vector3 planeC = planeB.copy().rotate(45 * MathHelper.torad, dirVec).normalize();
        Vector3 planeD = planeB.copy().rotate(-45 * MathHelper.torad, dirVec).normalize();
        planeA.multiply(scale);
        planeB.multiply(scale);
        planeC.multiply(scale);
        planeD.multiply(scale);
        double dist = 0.2 * (new Vec3d(source.x, source.y, source.z).distanceTo(new Vec3d(target.x, target.y, target.z)));

        double anim = (ClientEventHandler.elapsedTicks + partialTicks) / -15D;

        Vector3 p1 = source.copy().add(planeA);
        Vector3 p2 = target.copy().add(planeA);
        Vector3 p3 = source.copy().subtract(planeA);
        Vector3 p4 = target.copy().subtract(planeA);
        bufferQuad(buffer, p1, p2, p3, p4, anim, dist);

        p1 = source.copy().add(planeB);
        p2 = target.copy().add(planeB);
        p3 = source.copy().subtract(planeB);
        p4 = target.copy().subtract(planeB);
        bufferQuad(buffer, p1, p2, p3, p4, anim, dist);

        p1 = source.copy().add(planeC);
        p2 = target.copy().add(planeC);
        p3 = source.copy().subtract(planeC);
        p4 = target.copy().subtract(planeC);
        bufferQuad(buffer, p1, p2, p3, p4, anim, dist);

        p1 = source.copy().add(planeD);
        p2 = target.copy().add(planeD);
        p3 = source.copy().subtract(planeD);
        p4 = target.copy().subtract(planeD);
        bufferQuad(buffer, p1, p2, p3, p4, anim, dist);

    }

    private void bufferQuad(BufferBuilder buffer, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4, double anim, double dist) {

        buffer.pos(p1.x, p1.y, p1.z).tex(0, anim).endVertex();
        buffer.pos(p2.x, p2.y, p2.z).tex(0, dist + anim).endVertex();
        buffer.pos(p4.x, p4.y, p4.z).tex(1.0, dist + anim).endVertex();
        buffer.pos(p3.x, p3.y, p3.z).tex(1.0, anim).endVertex();
    }

}
