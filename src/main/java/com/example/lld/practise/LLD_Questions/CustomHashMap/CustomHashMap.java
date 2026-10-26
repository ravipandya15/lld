package com.example.lld.practise.LLD_Questions.CustomHashMap;

public class CustomHashMap<K, V> {
    int capacity = 16; // initial default capacity
    Entry<K, V>[] table; // Array of Entry object

    public CustomHashMap() {
        this.table = new Entry[capacity];
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> prevNode = null;
            Entry<K, V> curNode = table[index];

            while (curNode != null) {
                if (curNode.getKey().equals(key)) {
                    curNode.setValue(value);
                    return;
                }
                prevNode = curNode;
                curNode = curNode.getNext();
            }
            if (prevNode != null) {
                prevNode.setNext(newEntry);
            }
        }
    }

    public V get(K key) {
        V value = null;
        int index = getIndex(key);
        if (table[index] != null) {
            Entry<K, V> curNode = table[index];
            while (curNode != null) {
                if (curNode.getKey().equals(key)) {
                    value = curNode.getValue();
                    break;
                }
                curNode = curNode.getNext();
            }
        }
        return value;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] == null) return;
        Entry<K, V> curNode = table[index];
        Entry<K, V> prevNode = null;
        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                if (prevNode == null) {
                    table[index] = curNode.getNext();
                    break;
                } else {
                    prevNode.setNext(curNode.getNext());
                    break;
                }
            }
            prevNode = curNode;
            curNode = curNode.getNext();
        }
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> curNode = table[i];
                while (curNode != null) {
                    System.out.println("[" + curNode.getKey() + "]: " + curNode.getValue() + " -> ");
                    curNode = curNode.getNext();
                }
            }
        }
    }

    private int getIndex(K key) {
        if (key == null) return 0;

//        return Math.abs(key.hashCode() % capacity);
        return (key.hashCode() % capacity);
    }
}
