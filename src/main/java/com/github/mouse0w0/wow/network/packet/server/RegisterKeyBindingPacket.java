package com.github.mouse0w0.wow.network.packet.server;

import com.github.mouse0w0.wow.keybinding.Key;
import com.github.mouse0w0.wow.keybinding.KeyBinding;
import com.github.mouse0w0.wow.keybinding.KeyDomain;
import com.github.mouse0w0.wow.keybinding.KeyModifier;
import com.github.mouse0w0.wow.network.Packet;
import com.github.mouse0w0.wow.util.BufUtils;
import io.netty.buffer.ByteBuf;

public class RegisterKeyBindingPacket implements Packet {

    private int keyBindingId;
    private Key defaultKey;
    private KeyModifier defaultModifier;
    private KeyDomain domain;
    private String displayName;

    public RegisterKeyBindingPacket() {
    }

    public RegisterKeyBindingPacket(int keyBindingId, KeyBinding keyBinding) {
        this.keyBindingId = keyBindingId;
        defaultKey = keyBinding.getDefaultKey();
        defaultModifier = keyBinding.getDefaultModifier();
        domain = keyBinding.getDomain();
        displayName = keyBinding.getDisplayName();
    }

    @Override
    public void read(ByteBuf buffer) {
        keyBindingId = BufUtils.readVarInt(buffer);
        defaultKey = BufUtils.readEnum(buffer, Key.class);
        defaultModifier = BufUtils.readEnum(buffer, KeyModifier.class);
        domain = BufUtils.readEnum(buffer, KeyDomain.class);
        displayName = BufUtils.readString(buffer);
    }

    @Override
    public void write(ByteBuf buffer) {
        BufUtils.writeVarInt(buffer, keyBindingId);
        BufUtils.writeEnum(buffer, defaultKey);
        BufUtils.writeEnum(buffer, defaultModifier);
        BufUtils.writeEnum(buffer, domain);
        BufUtils.writeString(buffer, displayName);
    }
}
