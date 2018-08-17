package com.github.mouse0w0.wow.profile;

import com.github.mouse0w0.wow.network.packet.server.ServerVerificationPacket;

import java.util.UUID;

public class Server implements Profile {

    public static final Server UNSUPPORTED_SERVER = new Server();

    private static final UUID ZERO = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private final boolean support;
    private final int version;
    private final UUID uuid;

    private Server() {
        version = 0;
        support = false;
        uuid = ZERO;
    }

    public Server(ServerVerificationPacket packet) {
        version = packet.getInternalVersion();
        support = true;
        uuid = packet.getUUID();
    }

    public boolean isSupport() {
        return support;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
