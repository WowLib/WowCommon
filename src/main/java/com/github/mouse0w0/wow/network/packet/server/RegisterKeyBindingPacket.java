package com.github.mouse0w0.wow.network.packet.server;

import com.github.mouse0w0.wow.keybinding.*;
import com.github.mouse0w0.wow.network.Packet;
import com.github.mouse0w0.wow.registry.Registry;
import com.github.mouse0w0.wow.util.BufUtils;
import com.github.mouse0w0.wow.util.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;

public class RegisterKeyBindingPacket implements Packet {

    private JsonArray json;

    public RegisterKeyBindingPacket() {
    }

    public RegisterKeyBindingPacket(Registry<ServerKeyBinding> serverKeyBindings) {
        json = new JsonArray();
        for (ServerKeyBinding serverKeyBinding : serverKeyBindings.getValues()) {
            json.add(toJsonObject(serverKeyBindings.getId(serverKeyBinding), serverKeyBinding));
        }
    }

    private JsonObject toJsonObject(Integer id, ServerKeyBinding serverKeyBinding) {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", serverKeyBinding.getRegistryName().toString());
        json.addProperty("key", serverKeyBinding.getDefaultKey().getCode());
        json.addProperty("mod", serverKeyBinding.getDefaultModifier().ordinal());
        json.addProperty("domain", serverKeyBinding.getDomain().ordinal());
        json.addProperty("display", serverKeyBinding.getDisplayName());
        return json;
    }

    public JsonArray getJson() {
        return json;
    }

    @Override
    public void read(ByteBuf buffer) {
        json = GsonUtils.JSON_PARSER.parse(BufUtils.readString(buffer)).getAsJsonArray();
    }

    @Override
    public void write(ByteBuf buffer) {
        BufUtils.writeString(buffer, json.toString());
    }
}
