package com.github.mouse0w0.wow.network;

public interface NetworkManager {

    <T extends Packet> void register(Class<T> packetType, PacketHandler<T> handler);

    void send(Object target, Packet packet);

    void handle(Object sender, byte[] bytes);
}