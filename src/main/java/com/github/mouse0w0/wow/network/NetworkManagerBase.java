package com.github.mouse0w0.wow.network;

import java.util.HashMap;
import java.util.Map;

import com.github.mouse0w0.wow.util.BufUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public abstract class NetworkManagerBase implements NetworkManager {
    private final HashMap<Object, PacketStorage> storages = new HashMap<Object, PacketStorage>() {
        public PacketStorage get(Object key) {
            if (!storages.containsKey(key)) {
                return this.put(key, new PacketStorage());
            }
            return super.get(key);

        }
    };

    private final Map<Byte, RegisteredPacket<?>> idToRegisteredPackets = new HashMap<>();
    private final Map<Class<?>, RegisteredPacket<?>> typeToRegisteredPacket = new HashMap<>();
    private byte nextId = 0;

    public <T extends Packet> void register(Class<T> packetType, PacketHandler<T> handler) {
        if (typeToRegisteredPacket.containsKey(packetType))
            throw new NetworkException("The packet has been registered.");
        RegisteredPacket<?> registeredPacket = new RegisteredPacket(packetType, handler, nextId);
        idToRegisteredPackets.put(nextId, registeredPacket);
        typeToRegisteredPacket.put(packetType, registeredPacket);
        nextId++;
    }

    private static final int MAXIUM_SIZE = 30000;
    private static final int SPLIT_SIZE = 10000;

    @Override
    public void send(Object target, Packet packet) {
        ByteBuf buf = createBuffer(packet.getClass());
        packet.write(buf);
        int length = 0;
        if ((length = buf.readableBytes()) > MAXIUM_SIZE) {
            int total = (int) Math.ceil(length / SPLIT_SIZE);
            for (int i = 0; i < total; i++) {
                send(target, wrapSplitBuffer(packet.hashCode(), total, i, BufUtils.readBytes(buf, SPLIT_SIZE)));
            }
        } else {
            send(target, Unpooled.buffer().writeBoolean(false).writeBytes(buf));
        }
    }

    public abstract void send(Object target, ByteBuf buf);

    public void handle(Object sender, ByteBuf buf) {
        if (!buf.readBoolean()) { // Do original logic with non-split packets
            byte id = buf.readByte();
            RegisteredPacket<?> registeredPacket = idToRegisteredPackets.get(id);
            if (registeredPacket == null)
                throw new NetworkException("Couldn't handle this packet.");

            registeredPacket.handle(sender, buf);
        } else { // Or otherwise assemble packets
            PacketStorage currentStorage = storages.get(sender);
            SplitPacket splitPacket = currentStorage.appendOrCreate(buf);
            if (splitPacket.incomplete()) {
                return;
            }
            ByteBuf original = splitPacket.assemble();
            currentStorage.remove(splitPacket);
            this.handle(sender, original);// re-handle this packet
        }
    }

    protected ByteBuf createBuffer(Class<? extends Packet> packetType) {
        RegisteredPacket<?> registeredPacket = typeToRegisteredPacket.get(packetType);
        if (registeredPacket == null)
            throw new NetworkException("Couldn't create this packet buffer.");

        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(registeredPacket.id);
        return buf;
    }

    protected ByteBuf wrapSplitBuffer(int identifier, int total, int current, byte[] split) {
        ByteBuf splitBuf = Unpooled.buffer();
        splitBuf.writeBoolean(true);// Mark as split packet
        splitBuf.writeInt(identifier);
        splitBuf.writeInt(total);
        splitBuf.writeInt(current);
        splitBuf.writeBytes(split);
        return splitBuf;
    }

    protected class SplitPacket extends HashMap<Integer, byte[]> {
        int hash;
        int total;

        public SplitPacket(int hash, int total) {
            this.hash = hash;
            this.total = total;
        }

        public byte[] put(Integer key, ByteBuf buf) {
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            return this.put(key, bytes);
        }

        public ByteBuf assemble() {
            ByteBuf original = Unpooled.buffer();
            original.writeBoolean(false);// Sign the packet is non-split
            for (int i = 0; i < total; i++) {
                original.writeBytes(this.get(i));// Assemble the packet in order
            }
            return original;
        }

        public boolean incomplete() {
            return this.size() < total;
        }
    }

    protected class PacketStorage {
        HashMap<Integer, SplitPacket> storage = new HashMap<>();

        public SplitPacket appendOrCreate(ByteBuf split) {
            int hash = split.readInt();
            int totalPackets = split.readInt();
            int currentPacketIndex = split.readInt();
            SplitPacket splitPacket;
            if (!storage.containsKey(hash)) {
                splitPacket = storage.put(hash, new SplitPacket(hash, totalPackets));
            }
            splitPacket = storage.get(hash);
            splitPacket.put(currentPacketIndex, split);
            return splitPacket;
        }

        public void remove(SplitPacket splitPacket) {
            storage.remove(splitPacket.hash);
        }
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
