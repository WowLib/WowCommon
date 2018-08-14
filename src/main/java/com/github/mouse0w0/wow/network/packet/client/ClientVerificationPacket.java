package com.github.mouse0w0.wow.network.packet.client;

import com.github.mouse0w0.wow.network.Packet;
import io.netty.buffer.ByteBuf;

public class ClientVerificationPacket implements Packet {

    private int internalVersion;

    public ClientVerificationPacket(){}

    public ClientVerificationPacket(int internalVersion){
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
