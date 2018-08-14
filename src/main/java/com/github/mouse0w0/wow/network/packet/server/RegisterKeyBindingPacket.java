package com.github.mouse0w0.wow.network.packet.server;

import com.github.mouse0w0.wow.keybinding.Key;
import com.github.mouse0w0.wow.keybinding.KeyDomain;
import com.github.mouse0w0.wow.keybinding.KeyModifier;
import com.github.mouse0w0.wow.network.Packet;
import io.netty.buffer.ByteBuf;

public class RegisterKeyBindingPacket implements Packet {

    private int keyBindingId;
    private Key defaultKey;
    private KeyModifier defaultModifier;
    private KeyDomain keyDomain;

    @Override
    public void read(ByteBuf buffer) {

    }

    @Override
    public void write(ByteBuf buffer) {

    }
}
