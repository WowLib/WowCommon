package com.github.mouse0w0.wow.network;

import io.netty.buffer.ByteBuf;

public interface NetworkManager {

    <T extends Packet> void register(Class<T> packetType, PacketHandler<T> handler);

    void send(Object target, Packet packet);

    void handle(Object sender, ByteBuf buf);
}