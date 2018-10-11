package com.github.mouse0w0.wow.keybinding;

public class ServerKeyBinding extends KeyBinding<ServerKeyBinding> {

    public static class Builder {

        private Key defaultKey;
        private KeyModifier defaultModifier;
        private KeyDomain domain;
        private String displayName;

        private Runnable onPress;
        private Runnable onRelease;

        public Builder create() {
            return new Builder();
        }

        public Builder defaultKey(Key key) {
            this.defaultKey = key;
            return this;
        }

        public Builder defaultModifier(KeyModifier keyModifier) {
            this.defaultModifier = keyModifier;
            return this;
        }

        public Builder domain(KeyDomain domain) {
            this.domain = domain;
            return this;
        }

        public Builder onPress(Runnable onPress) {
            this.onPress = onPress;
            return this;
        }

        public Builder onRelease(Runnable onRelease) {
            this.onRelease = onRelease;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public ServerKeyBinding build() {
            return new ServerKeyBinding(defaultKey, defaultModifier, domain, displayName, onPress, onRelease);
        }
    }

    private final Runnable onPress;
    private final Runnable onRelease;

    private ServerKeyBinding(Key defaultKey, KeyModifier defaultModifier, KeyDomain domain, String displayName, Runnable onPress, Runnable onRelease) {
        super(defaultKey, defaultModifier, domain, displayName);
        this.onPress = onPress;
        this.onRelease = onRelease;
    }

    public void onPress() {
        if (onPress != null)
            onPress.run();
    }

    public void onRelease() {
        if (onRelease != null)
            onRelease.run();
    }
}
