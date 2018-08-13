package com.github.mouse0w0.wow.data;

import com.github.mouse0w0.wow.network.packet.common.VerificationPacket;

public class Server {

    private boolean support = false;
    private int version = 0;

    public boolean isSupport() {
        return support;
    }

    public int getServerVersion() {
        return version;
    }

    public void load(VerificationPacket packet) {
        version = packet.getInternalVersion();
        support = true;
    }
}
