package com.github.mouse0w0.wow.client.keybinging;

import com.github.mouse0w0.wow.keybinding.Key;
import com.github.mouse0w0.wow.keybinding.KeyBinding;
import com.github.mouse0w0.wow.keybinding.KeyDomain;
import com.github.mouse0w0.wow.keybinding.KeyModifier;

public class KeyBindingClient extends KeyBinding {

    private Key key;
    private KeyModifier keyModifier;

    public KeyBindingClient(Key defaultKey, KeyModifier defaultModifier, KeyDomain domain, String displayName) {
        super(defaultKey, defaultModifier, domain, displayName);
        resetToDefault();
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public KeyModifier getKeyModifier() {
        return keyModifier;
    }

    public void setKeyModifier(KeyModifier keyModifier) {
        this.keyModifier = keyModifier;
    }

    public void resetToDefault() {
        setKey(getDefaultKey());
        setKeyModifier(getDefaultModifier());
    }
}
