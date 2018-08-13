package com.github.mouse0w0.wow.network;

public interface NetworkManager {

    <T extends Packet> void register(Class<T> packetType, PacketHandler<T> handler);

    void send(Connection target, Packet packet);

    void handle(Connection sender, byte[] bytes);
}