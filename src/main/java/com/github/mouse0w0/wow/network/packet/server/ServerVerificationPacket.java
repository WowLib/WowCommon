package com.github.mouse0w0.wow.network.packet.server;

import com.github.mouse0w0.wow.network.Packet;
import com.github.mouse0w0.wow.util.BufUtils;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class ServerVerificationPacket implements Packet {

    private int internalVersion;
    private UUID uuid;

    public ServerVerificationPacket() {
    }

    public ServerVerificationPacket(int internalVersion, UUID uuid) {
        this.internalVersion = internalVersion;
        this.uuid = uuid;
    }

    public int getInternalVersion() {
        return internalVersion;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void read(ByteBuf buffer) {
        internalVersion = buffer.readInt();
        uuid = UUID.fromString(BufUtils.readString(buffer));
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(internalVersion);
        BufUtils.writeString(buffer, uuid.toString());
    }
}
