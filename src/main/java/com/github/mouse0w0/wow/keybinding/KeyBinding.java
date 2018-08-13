package com.github.mouse0w0.wow.keybinding;

public class KeyBinding {

    private final Key defaultKey;
    private final KeyModifier defaultModifier;
    private final KeyDomain domain;

    private Key key;
    private KeyModifier modifier;

    public KeyBinding(Key defaultKey, KeyModifier defaultModifier, KeyDomain domain) {
        this.defaultKey = defaultKey;
        this.defaultModifier = defaultModifier;
        this.domain = domain;
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

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public KeyModifier getModifier() {
        return modifier;
    }

    public void setModifier(KeyModifier modifier) {
        this.modifier = modifier;
    }
}
