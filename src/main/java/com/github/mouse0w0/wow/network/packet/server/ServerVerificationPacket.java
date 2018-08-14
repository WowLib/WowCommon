package com.github.mouse0w0.wow.network.packet.server;

import com.github.mouse0w0.wow.network.Packet;
import io.netty.buffer.ByteBuf;

public class ServerVerificationPacket implements Packet {

    private int internalVersion;

    public ServerVerificationPacket(){}

    public ServerVerificationPacket(int internalVersion){
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
