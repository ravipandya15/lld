package com.example.lld.practise.LLD_Questions.CustomHashMap;

import lombok.Data;

@Data
public class Entry<K, V> { //creating a generic class
    K key;
    V value;
    Entry<K, V> next;

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
