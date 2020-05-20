package com.project.task.util.HashMaps.impl;

import com.project.task.util.HashMaps.HashMap;

import java.util.Set;


public class AlienHashMap<K, V> implements HashMap<K, V> {
    private java.util.HashMap<K, V> hashMap;

    public AlienHashMap(){
        hashMap = new java.util.HashMap<K, V>();
    }

    public Set<K> keySet() {
        return hashMap.keySet();
    }

    public Object get(K key) {
        return hashMap.get(key);
    }

    public void put(K key, V value) {
        hashMap.put(key,value);
    }
}
