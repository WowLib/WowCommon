package com.github.mouse0w0.wow.profile;

import java.util.UUID;

public interface User extends Profile {

    UUID getUUID();

    String getName();

    Object getSource();
}
