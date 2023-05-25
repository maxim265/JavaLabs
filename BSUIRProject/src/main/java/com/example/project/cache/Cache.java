package com.example.project.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//extends, super
@Component
public class Cache<K, V>  {
    public final Map<K, V> cache = new HashMap<>();

    public boolean isContains(K key) {
        return cache.containsKey(key);
    }

    public V get(K key){
        return cache.get(key);
    }

    public void push(K key, V value){
        cache.put(key, value);
    }
}
