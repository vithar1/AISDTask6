package com.project.task.util.HashMaps;

import java.util.Set;

public interface HashMap<K, V> {
    Set<K> keySet();
    Object get(K key);
    void put(K key, V value);
}
