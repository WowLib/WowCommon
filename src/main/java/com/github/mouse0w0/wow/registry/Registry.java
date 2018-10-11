package com.github.mouse0w0.wow.registry;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Registry<T extends RegistryEntry<T>> {

    Class<T> getRegistryEntryType();

    T register(T obj) throws RegisterException;

    default void registerAll(T... objs) throws RegisterException {
        for (T obj : objs)
            register(obj);
    }

    T getValue(NamespacedKey registryName);

    default T getValue(String domain, String path) {
        return getValue(new NamespacedKey(domain, path));
    }

    default T getValue(String registryName) {
        return getValue(new NamespacedKey(registryName));
    }

    NamespacedKey getKey(T value);

    boolean containsKey(NamespacedKey key);

    boolean containsValue(T value);

    int getId(T obj);

    int getId(NamespacedKey key);

    NamespacedKey getKey(int id);

    T getValue(int id);

    Set<NamespacedKey> getKeys();

    Collection<T> getValues();

    Collection<Map.Entry<NamespacedKey, T>> getEntries();

    Collection<Map.Entry<NamespacedKey, Integer>> getNameToId();
}
