package com.github.mouse0w0.wow.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.HashMap;
import java.util.Map;

public abstract class NetworkManagerBase implements NetworkManager {

    private final Map<Byte, RegisteredPacket<?>> idToRegisteredPackets = new HashMap<>();
    private final Map<Class<?>, RegisteredPacket<?>> typeToRegisteredPacket = new HashMap<>();
    private byte nextId = 0;

    public <T extends Packet> void register(Class<T> packetType, PacketHandler<T> handler) {
        if (typeToRegisteredPacket.containsKey(packetType))
            throw new NetworkException("The packet has been registered.");
        RegisteredPacket<T> registeredPacket = new RegisteredPacket<>(packetType, handler, nextId);
        idToRegisteredPackets.put(nextId, registeredPacket);
        typeToRegisteredPacket.put(packetType, registeredPacket);
        nextId++;
    }

    public void handle(Object sender, ByteBuf buf) {
        byte id = buf.readByte();
        RegisteredPacket<?> registeredPacket = idToRegisteredPackets.get(id);
        if (registeredPacket == null)
            throw new NetworkException("Couldn't handle this packet.");

        registeredPacket.handle(sender, buf);
    }

    protected ByteBuf createBuffer(Class<? extends Packet> packetType) {
        RegisteredPacket<?> registeredPacket = typeToRegisteredPacket.get(packetType);
        if (registeredPacket == null)
            throw new NetworkException("Couldn't create this packet buffer.");

        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(registeredPacket.id);
        return buf;
    }

    private class RegisteredPacket<T extends Packet> {
        private final Class<T> packetType;
        private final PacketHandler<T> handler;
        private final byte id;

        public RegisteredPacket(Class<T> packetType, PacketHandler<T> handler, byte id) {
            this.packetType = packetType;
            this.handler = handler;
            this.id = id;
        }

        public void handle(Object sender, ByteBuf buf) {
            if (handler == null)
                throw new NetworkException("Couldn't handle this packet.");

            try {
                T packet = packetType.newInstance();
                packet.read(buf);
                handler.hander(sender, packet);
            } catch (ReflectiveOperationException e) {
                throw new NetworkException("Couldn't handle this packet.", e);
            }
        }
    }
}
