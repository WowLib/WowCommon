package com.github.mouse0w0.wow.data;

import java.util.UUID;

public interface Player {

    UUID getUUID();

    String getName();

    boolean isSupport();

    int getVersion();
}
