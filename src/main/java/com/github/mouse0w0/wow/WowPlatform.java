package com.github.mouse0w0.wow;

import com.github.mouse0w0.wow.network.NetworkManager;
import com.github.mouse0w0.wow.profile.Server;
import com.github.mouse0w0.wow.profile.User;
import com.github.mouse0w0.wow.registry.RegistryManager;

import java.util.UUID;

public class WowPlatform {
    private static final String NAME = "Wow";
    private static final String VERSION = "1.0.0";
    private static final int INTERNAL_VERSION = 1;

    private static final String NETWORK_CHANNEL_NAME = "Wow";

    private static PlatformProvider platformProvider;

    public static String getName() {
        return NAME;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static int getInternalVersion() {
        return INTERNAL_VERSION;
    }

    public static String getNetworkChannelName() {
        return NETWORK_CHANNEL_NAME;
    }

    public static NetworkManager getNetwork() {
        return platformProvider.getNetwork();
    }

    public static RegistryManager getRegistryManager() {
        return platformProvider.getRegistryManager();
    }

    public static boolean isServer() {
        return platformProvider.isServer();
    }

    public static boolean isClient() {
        return platformProvider.isClient();
    }

    /**
     * Return the server which are connecting if platform is client.
     * Or return the server if platform is server.
     *
     * @return The server
     */
    public static Server getServer() {
        return platformProvider.getServer();
    }

    /**
     * Return the user who are connecting server if platform is server.
     *
     * @return The user
     * @throws UnsupportedOperationException
     * @see PlatformProvider#isServer()
     */
    public static User getUser(UUID uuid) throws UnsupportedOperationException {
        return platformProvider.getUser(uuid);
    }

    /**
     * Return the user who are connecting server if platform is server.
     *
     * @return The user
     * @throws UnsupportedOperationException
     * @see PlatformProvider#isServer()
     */
    public static User getUser(String name) throws UnsupportedOperationException {
        return platformProvider.getUser(name);
    }

    /**
     * Return the client user if platform is client.
     *
     * @return The user
     * @throws UnsupportedOperationException
     * @see PlatformProvider#isClient()
     */
    public static User getUser() throws UnsupportedOperationException {
        return platformProvider.getUser();
    }

    public static void setPlatformProvider(PlatformProvider platformProvider) {
        if (WowPlatform.platformProvider != null)
            throw new IllegalStateException("Wow platform has been initialized.");
        WowPlatform.platformProvider = platformProvider;
    }
}
