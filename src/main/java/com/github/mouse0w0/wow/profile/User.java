package com.github.mouse0w0.wow.profile;

import java.util.UUID;

public interface User {

    UUID getUUID();

    String getName();

    boolean isSupport();

    int getVersion();
}
