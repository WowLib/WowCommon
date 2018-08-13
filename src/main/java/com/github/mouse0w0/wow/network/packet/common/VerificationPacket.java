package com.github.mouse0w0.wow.network.packet.common;

import com.github.mouse0w0.wow.network.Packet;
import io.netty.buffer.ByteBuf;

public class VerificationPacket implements Packet {

    private int internalVersion;

    public VerificationPacket(){}

    public VerificationPacket(int internalVersion){
        this.internalVersion = internalVersion;
    }

    public int getInternalVersion() {
        return internalVersion;
    }

    @Override
    public void read(ByteBuf buffer) {
        internalVersion = buffer.readInt();
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(internalVersion);
    }
}
