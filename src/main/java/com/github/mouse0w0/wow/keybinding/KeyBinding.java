package com.github.mouse0w0.wow.keybinding;

import com.github.mouse0w0.wow.registry.NamespacedKey;
import com.github.mouse0w0.wow.registry.RegistryEntry;
import com.google.common.reflect.TypeToken;

public abstract class KeyBinding<T extends KeyBinding> implements RegistryEntry<T> {

    private final TypeToken<T> token = new TypeToken<T>(getClass()) {
    };
    private NamespacedKey key;

    @Override
    public NamespacedKey getRegistryName() {
        return key;
    }

    @Override
    public Class<T> getRegistryType() {
        return (Class<T>) token.getRawType();
    }

    @Override
    public T setRegistryName(NamespacedKey key) {
        if (this.key != null)
            throw new IllegalStateException("Duplicated register");
        this.key = key;
        return (T) this;
    }

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
