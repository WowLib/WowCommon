package com.github.mouse0w0.wow;

import com.github.mouse0w0.wow.network.NetworkManager;
import com.github.mouse0w0.wow.profile.Profile;
import com.github.mouse0w0.wow.profile.Server;
import com.github.mouse0w0.wow.profile.User;
import com.github.mouse0w0.wow.registry.RegistryManager;

import java.util.UUID;

public interface PlatformProvider {

    NetworkManager getNetwork();

    RegistryManager getRegistryManager();

    boolean isServer();

    default boolean isClient() {
        return !isServer();
    }

    /**
     * Return the server which are connecting if platform is client.
     * Or return the server if platform is server.
     * @return The server
     */
    Server getServer();

    /**
     * Return the user who are connecting server if platform is server.
     * @see PlatformProvider#isServer()
     * @return The user
     * @throws UnsupportedOperationException
     */
    User getUser(UUID uuid) throws UnsupportedOperationException;

    /**
     * Return the user who are connecting server if platform is server.
     * @see PlatformProvider#isServer()
     * @return The user
     * @throws UnsupportedOperationException
     */
    User getUser(String name) throws UnsupportedOperationException;

    /**
     * Return the client user if platform is client.
     * @see PlatformProvider#isClient()
     * @return The user
     * @throws UnsupportedOperationException
     */
    User getUser() throws UnsupportedOperationException;
}
