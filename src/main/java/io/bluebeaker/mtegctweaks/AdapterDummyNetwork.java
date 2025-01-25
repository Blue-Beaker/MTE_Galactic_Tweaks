package io.bluebeaker.mtegctweaks;

import com.google.common.collect.ImmutableSet;
import micdoodle8.mods.galacticraft.api.transmission.tile.IBufferTransmitter;
import micdoodle8.mods.galacticraft.core.fluid.FluidNetwork;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AdapterDummyNetwork extends FluidNetwork {

    public final TileEntity tile;
    public final IFluidHandler handler;
    public final EnumFacing side;

    public AdapterDummyNetwork(TileEntity tile,EnumFacing side){
        this.tile=tile;
        this.side=side;
        handler=this.tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,side);
    }

    @Override
    public void adoptNetwork(FluidNetwork network) {
    }

    @Override
    public void register() {
//        super.register();
    }

    @Override
    public void unregister() {
//        super.unregister();
    }

    @Override
    public void addTransmitter(IBufferTransmitter<FluidStack> transmitter) {
//        super.addTransmitter(transmitter);
    }

    @Override
    public void removeTransmitter(IBufferTransmitter<FluidStack> transmitter) {
//        super.removeTransmitter(transmitter);
    }

    @Override
    public void onTransmitterAdded(IBufferTransmitter<FluidStack> transmitter) {
//        super.onTransmitterAdded(transmitter);
    }

    @Override
    public void clamp() {
    }

    @Override
    public void updateCapacity() {
    }

    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getRequest() {
        return getCapacity();
    }

    @Override
    public int emitToBuffer(FluidStack toSend, boolean doTransfer) {
        return this.handler.fill(toSend,doTransfer);
    }

    @Override
    public void tickEnd() {
    }

    @Override
    public void addUpdate(EntityPlayerMP player) {
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void clientTick() {
    }

    @Override
    public float getScale() {
        return 0;
    }

    @Override
    public List<Pair<BlockPos, Map<EnumFacing, IFluidHandler>>> getAcceptors(FluidStack toSend) {
        return Collections.emptyList();
    }

    @Override
    public void refresh() {
    }

    @Override
    public void refreshAcceptors() {
    }

    @Override
    public ImmutableSet<IBufferTransmitter<FluidStack>> getTransmitters() {
        return ImmutableSet.of();
    }

    @Override
    public FluidNetwork merge(FluidNetwork network) {
        return this;
    }

    @Override
    public void split(IBufferTransmitter<FluidStack> splitPoint) {
    }
}
