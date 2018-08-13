package com.github.mouse0w0.wow.network;

import io.netty.buffer.ByteBuf;

public interface Packet {

    void read(ByteBuf buffer);

    void write(ByteBuf buffer);
}
