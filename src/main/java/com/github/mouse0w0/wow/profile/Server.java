package com.github.mouse0w0.wow.profile;

import java.util.UUID;

public class Server implements Profile {

    private static final UUID UUID_ZERO = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public static final Server UNSUPPORTED_SERVER = new Server(false, 0, UUID_ZERO, null);

    private final boolean support;
    private final int version;
    private final UUID uuid;
    private final String address;

    public Server(boolean support, int version, UUID uuid, String address) {
        this.support = support;
        this.version = version;
        this.uuid = uuid;
        this.address = address;
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

    public String getAddress() { return address; }
}
