package com.github.mouse0w0.wow.util;

import io.netty.buffer.ByteBuf;

public final class BufUtils {
    private BufUtils() {}

    public static void writeVarInt(ByteBuf buf, int value) {
        while ((value & -128) != 0) {
            buf.writeByte(value & 127 | 128);
            value >>>= 7;
        }

        buf.writeByte(value);
    }

    public static int readVarInt(ByteBuf buf) {
        int i = 0;
        int j = 0;

        while (true) {
            byte b0 = buf.readByte();
            i |= (b0 & 0x7F) << j++ * 7;

            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }

            if ((b0 & 0x80) != 0x80) {
                break;
            }
        }

        return i;
    }

    public static void writeString(ByteBuf buf, String value) {
        byte[] bytes = value.getBytes();
        writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    public static String readString(ByteBuf buf) {
        int length = readVarInt(buf);
        return new String(buf.readBytes(length).array());
    }

    public static <T extends Enum<T>> void writeEnum(ByteBuf buf, T value) {
        writeVarInt(buf, value.ordinal());
    }

    public static <T extends Enum<T>> T readEnum(ByteBuf buf, Class<T> type) {
        return type.getEnumConstants()[readVarInt(buf)];
    }

    public static byte[] readBytes(ByteBuf buf, int length) {
        int readable = buf.readableBytes();
        byte[] bytes = new byte[length > readable ? readable : length];
        buf.readBytes(bytes);
        return bytes;
    }
}
