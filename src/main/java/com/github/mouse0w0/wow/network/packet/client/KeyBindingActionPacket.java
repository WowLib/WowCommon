package com.github.mouse0w0.wow.network.packet.client;

import com.github.mouse0w0.wow.keybinding.KeyAction;
import com.github.mouse0w0.wow.network.Packet;
import com.github.mouse0w0.wow.util.BufUtils;
import io.netty.buffer.ByteBuf;

public class KeyBindingActionPacket implements Packet {

    private int keyBindingId;
    private KeyAction action;

    public int getKeyBindingId() {
        return keyBindingId;
    }

    public KeyAction getAction() {
        return action;
    }

    @Override
    public void read(ByteBuf buffer) {
        keyBindingId = BufUtils.readVarInt(buffer);
        action = KeyAction.values()[buffer.readByte()];
    }

    @Override
    public void write(ByteBuf buffer) {
        BufUtils.writeVarInt(buffer, keyBindingId);
        buffer.writeByte(action.ordinal());
    }
}
