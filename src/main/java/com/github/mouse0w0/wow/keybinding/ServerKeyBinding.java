package com.github.mouse0w0.wow.keybinding;

import com.github.mouse0w0.wow.profile.User;

import java.util.function.Consumer;

public class ServerKeyBinding extends KeyBinding<ServerKeyBinding> {

    public static class Builder {

        private Key defaultKey;
        private KeyModifier defaultModifier;
        private KeyDomain domain;
        private String displayName;

        private Consumer<User> onPress;
        private Consumer<User> onRelease;

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

        public Builder onPress(Consumer<User> onPress) {
            this.onPress = onPress;
            return this;
        }

        public Builder onRelease(Consumer<User> onRelease) {
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

    private final Consumer<User> onPress;
    private final Consumer<User> onRelease;

    private ServerKeyBinding(Key defaultKey, KeyModifier defaultModifier, KeyDomain domain, String displayName, Consumer<User> onPress, Consumer<User> onRelease) {
        super(defaultKey, defaultModifier, domain, displayName);
        this.onPress = onPress;
        this.onRelease = onRelease;
    }

    public void onPress(User user) {
        if (onPress != null)
            onPress.accept(user);
    }

    public void onRelease(User user) {
        if (onRelease != null)
            onRelease.accept(user);
    }
}
