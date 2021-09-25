package com.warmthdawn.mod.gugu_utils.modularmachenary.aura;

import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAura;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import de.ellpeck.naturesaura.packet.PacketHandler;
import de.ellpeck.naturesaura.packet.PacketParticleStream;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TileAuraInputHatch extends TileAuraHatch implements ITickable, MachineComponentTile, IConsumable<RequirementAura.RT> {
    private int lastAura;
    private int drains = 0;

    public void sendToClients() {
        WorldServer world = (WorldServer) this.getWorld();
        PlayerChunkMapEntry entry = world.getPlayerChunkMap().getEntry(this.getPos().getX() >> 4, this.getPos().getZ() >> 4);
        if (entry != null) {
            entry.sendPacket(this.getUpdatePacket());
        }
    }


    private void drainAuraForce(int amount) {

        Random rand = this.world.rand;
        BlockPos spot = IAuraChunk.getHighestSpot(this.world, this.pos, 20, this.pos);
        IAuraChunk chunk = IAuraChunk.getAuraChunk(this.world, spot);

        chunk.drainAura(spot, amount);
        drains += amount;
        if (drains > 1000) {
            float scale = drains / 2500f;
            PacketHandler.sendToAllAround(this.world, this.pos, 32, new PacketParticleStream(
                this.pos.getX() + (float) rand.nextGaussian() * 6F,
                this.pos.getY() + rand.nextFloat() * 6F,
                this.pos.getZ() + (float) rand.nextGaussian() * 6F,
                this.pos.getX() + 0.5F, this.pos.getY() + 0.5F, this.pos.getZ() + 0.5F,
                rand.nextFloat() * 0.1F + 0.1F, IAuraType.forWorld(this.world).getColor(), rand.nextFloat() + scale
            ));
            drains = 0;
        }
    }

    @Override
    public void update() {
        Random rand = this.world.rand;
        if (!this.world.isRemote) {
            float sorePercent = (float) this.container.getStoredAura() / this.container.getMaxAura();

            double storeAttempt = 8;
            if (sorePercent > 0.6) {
                storeAttempt = 1.1 - sorePercent;
            } else if (sorePercent > 0.4) {
                storeAttempt = 2 * (1.1 - sorePercent);
            } else if (sorePercent > 0.2) {
                storeAttempt = 5 * (1.1 - sorePercent);
            }
            int space = this.container.storeAura((int) (800 * storeAttempt), true);
            if (space > 0 && this.container.isAcceptableType(IAuraType.forWorld(this.world))) {
                int toStore = Math.min(IAuraChunk.getAuraInArea(this.world, this.pos, 20), space);
                if (toStore > 0) {
                    drainAuraForce(toStore);
                    this.container.storeAura(toStore, false);
                }

            }

            if (this.world.getTotalWorldTime() % 10 == 0 && this.lastAura != this.container.getStoredAura()) {
                this.lastAura = this.container.getStoredAura();
                this.sendToClients();
            }
        } else {
            BlockPos pos = this.getPos().offset(EnumFacing.UP);

            if (!world.getBlockState(pos).isOpaqueCube() && rand.nextFloat() >= 0.7F) {
                if (this.container.getStoredAura() > 0) {
                    NaturesAuraAPI.instance().spawnMagicParticle(
                        this.pos.getX() + rand.nextFloat(), this.pos.getY() + 1F, this.pos.getZ() + rand.nextFloat(),
                        0F, 0F, 0F, this.container.getAuraColor(), rand.nextFloat() * 3F + 1F, rand.nextInt(100) + 50, -0.05F, true, true);
                }

            }

        }
    }

    @Override
    public boolean consume(RequirementAura.RT outputToken, boolean doOperation) {
        int consume = this.container.drainAura(outputToken.getAura(), !doOperation);
        outputToken.setAura(outputToken.getAura() - consume);
        if (outputToken.isForceDrain() && outputToken.getAura() > 0) {
            if (doOperation) {
                this.drainAuraForce(outputToken.getAura());
            }
            consume += outputToken.getAura();
            outputToken.setAura(0);
        }
        return consume > 0;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, (ComponentType) MMCompoments.COMPONENT_AURA);
    }
}
