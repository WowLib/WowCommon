package com.github.mouse0w0.wow.network.packet.client;

import com.github.mouse0w0.wow.keybinding.KeyAction;
import com.github.mouse0w0.wow.network.Packet;
import com.github.mouse0w0.wow.util.BufUtils;
import io.netty.buffer.ByteBuf;

public class KeyBindingActionPacket implements Packet {

    private int keyBindingId;
    private boolean pressed;

    public KeyBindingActionPacket() {
    }

    public KeyBindingActionPacket(int keyBindingId, boolean pressed) {
        this.keyBindingId = keyBindingId;
        this.pressed = pressed;
    }

    public int getKeyBindingId() {
        return keyBindingId;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void read(ByteBuf buffer) {
        keyBindingId = BufUtils.readVarInt(buffer);
        pressed = buffer.readBoolean();
    }

    @Override
    public void write(ByteBuf buffer) {
        BufUtils.writeVarInt(buffer, keyBindingId);
        buffer.writeBoolean(pressed);
    }
}
