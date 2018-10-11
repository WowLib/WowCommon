package com.github.mouse0w0.wow;

import com.github.mouse0w0.wow.network.NetworkManager;
import com.github.mouse0w0.wow.registry.RegistryManager;

public interface PlatformProvider {

    NetworkManager getNetwork();

    RegistryManager getRegistryManager();

    boolean isServer();

    default boolean isClient() { return !isServer();}
}
