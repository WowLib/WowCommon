package com.github.mouse0w0.wow.keybinding;

import com.github.mouse0w0.wow.registry.RegistryEntry;

public abstract class KeyBinding extends RegistryEntry.Impl<KeyBinding> {

    private final Key defaultKey;
    private final KeyModifier defaultModifier;
    private final KeyDomain domain;
    private final String displayName;

    protected KeyBinding(Key defaultKey, KeyModifier defaultModifier, KeyDomain domain, String displayName) {
        this.defaultKey = defaultKey;
        this.defaultModifier = defaultModifier;
        this.domain = domain;
        this.displayName = displayName;
    }

    public Key getDefaultKey() {
        return defaultKey;
    }

    public KeyModifier getDefaultModifier() {
        return defaultModifier;
    }

    public KeyDomain getDomain() {
        return domain;
    }

    public String getDisplayName() {
        return displayName;
    }
}
