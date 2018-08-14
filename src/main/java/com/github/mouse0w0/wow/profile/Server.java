package com.github.mouse0w0.wow.profile;

import com.github.mouse0w0.wow.network.packet.server.ServerVerificationPacket;

public class Server implements Profile {

    public static final Server UNSUPPORTED_SERVER = new Server();

    private boolean support = false;
    private int version = 0;

    private Server() {
        version = 0;
        support = false;
    }

    public Server(ServerVerificationPacket packet) {
        version = packet.getInternalVersion();
        support = true;
    }

    public boolean isSupport() {
        return support;
    }

    public int getVersion() {
        return version;
    }
}
