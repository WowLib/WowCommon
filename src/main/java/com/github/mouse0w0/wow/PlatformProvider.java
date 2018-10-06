package com.github.mouse0w0.wow;

import com.github.mouse0w0.wow.keybinding.KeyBinding;
import com.github.mouse0w0.wow.network.NetworkManager;
import com.github.mouse0w0.wow.registry.Registry;

public interface PlatformProvider {

    NetworkManager getNetwork();

    Registry<KeyBinding> getKeyBindingRegistry();

    boolean isServer();

    default boolean isClient() { return !isServer();}
}
