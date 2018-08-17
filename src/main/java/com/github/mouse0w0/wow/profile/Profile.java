package com.github.mouse0w0.wow.profile;

import java.util.UUID;

public interface Profile {

    boolean isSupport();

    int getVersion();

    UUID getUUID();
}
