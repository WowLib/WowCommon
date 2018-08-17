package com.github.mouse0w0.wow.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.reflect.TypeToken;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SimpleRegistry<T extends RegistryEntry<T>> implements Registry<T> {

    @SuppressWarnings("serial")
    private final TypeToken<T> token = new TypeToken<T>(getClass()) {
    };

    private final BiMap<NamespacedKey, T> registeredItems = HashBiMap.create();
    private final BiMap<Integer, T> idToRegisteredItems = HashBiMap.create();
    private final BiMap<NamespacedKey, Integer> keyToId = HashBiMap.create();

    public SimpleRegistry() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getRegistryEntryType() {
        return (Class<T>) token.getRawType();
    }

    @Override
    public T register(T obj) {
        NamespacedKey key = obj.getRegistryName();
        if (registeredItems.containsKey(key))
            throw new RegisterException("\"" + key + "\" has been registered.");

        registeredItems.put(key, obj);
        return obj;
    }

    @Override
    public T getValue(NamespacedKey key) {
        return registeredItems.get(key);
    }

    @Override
    public NamespacedKey getKey(T value) {
        return value.getRegistryName();
    }

    @Override
    public boolean containsKey(NamespacedKey key) {
        return registeredItems.containsKey(key);
    }

    @Override
    public boolean containsValue(T value) {
        return registeredItems.containsValue(value);
    }

    @Override
    public int getId(T obj) {
        return idToRegisteredItems.inverse().get(obj);
    }

    @Override
    public int getId(NamespacedKey key) {
        return keyToId.get(key);
    }

    @Override
    public NamespacedKey getKey(int id) {
        return keyToId.inverse().get(id);
    }

    @Override
    public T getValue(int id) {
        return idToRegisteredItems.get(id);
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return registeredItems.keySet();
    }

    @Override
    public Collection<T> getValues() {
        return registeredItems.values();
    }

    @Override
    public Collection<Map.Entry<NamespacedKey, T>> getEntries() {
        return registeredItems.entrySet();
    }

    @Override
    public Collection<Map.Entry<NamespacedKey, Integer>> getNameToId() {
        return keyToId.entrySet();
    }
}
