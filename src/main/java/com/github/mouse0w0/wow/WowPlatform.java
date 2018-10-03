package com.github.mouse0w0.wow;

import com.github.mouse0w0.wow.network.NetworkManager;

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

    public static void setPlatformProvider(PlatformProvider platformProvider) {
        if(platformProvider != null)
            throw new IllegalStateException("Wow platform has been initialized.");
        WowPlatform.platformProvider = platformProvider;
    }
}
