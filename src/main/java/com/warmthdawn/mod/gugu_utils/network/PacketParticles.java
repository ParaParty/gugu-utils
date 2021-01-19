package com.warmthdawn.mod.gugu_utils.network;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.client.particle.ParticleEnergyBall;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class PacketParticles implements IMessage {
    Vec3d spawn;
    Vec3d motion;
    Color color;
    float scale;
    int maxAge;

    public PacketParticles() {
    }

    public PacketParticles(Vec3d spawn, Vec3d motion, Color color, float scale, int maxAge) {
        this.spawn = spawn;
        this.motion = motion;
        this.color = color;
        this.scale = scale;
        this.maxAge = maxAge;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        spawn = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        motion = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        color = new Color(buf.readInt(), true);
        scale = buf.readFloat();
        maxAge = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(spawn.x);
        buf.writeDouble(spawn.y);
        buf.writeDouble(spawn.z);
        buf.writeDouble(motion.x);
        buf.writeDouble(motion.y);
        buf.writeDouble(motion.z);
        buf.writeInt(color.getRGB());
        buf.writeFloat(scale);
        buf.writeInt(maxAge);
    }

    public static class Handler implements IMessageHandler<PacketParticles, IMessage> {

        @Override
        public IMessage onMessage(PacketParticles message, MessageContext ctx) {
            GuGuUtils.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handle(PacketParticles message, MessageContext ctx) {
            EntityPlayer player = GuGuUtils.proxy.getClientPlayer();
            ParticleEnergyBall effect = new ParticleEnergyBall(
                    player.world,
                    message.spawn.x,
                    message.spawn.y,
                    message.spawn.z,
                    message.motion.x, message.motion.y, message.motion.z,
                    message.color.getRGB(), message.maxAge, message.scale);
            Minecraft.getMinecraft().effectRenderer.addEffect(effect);
        }
    }
}
