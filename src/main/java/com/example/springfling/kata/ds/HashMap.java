package com.example.springfling.kata.ds;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    public static int DEFAULT_CAPACITY = 16;
    private final int Size;
    private final ArrayList<HashSetEntry> Entries;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int size) {
        Entries = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Entries.add(null);
        }
        Size = size;
    }

    @Override
    public int size() {
        int size = 0;
        for (var entry : Entries) {
            if (entry != null) {
                var currentEntry = entry;
                while (currentEntry != null) {
                    size++;
                    currentEntry = entry.Next;
                }
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (var entry : Entries) {
            if (entry != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (var entry : Entries) {
            if (entry.Value.equals(value)) {
                return true;
            } else if (entry.Next != null) {
                var currentEntry = entry.Next;
                while (currentEntry != null) {
                    if (currentEntry.Value.equals(value)) {
                        return true;
                    }
                    currentEntry = currentEntry.Next;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        var entry = getEntry(key);
        return entry != null ? entry.Value : null;
    }

    @Override
    public V put(K key, V value) {
        return addEntry(key, value).Value;
    }

    @Override
    public V remove(Object key) {
        var entry = removeEntry(key);
        return entry != null ? entry.Value : null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < Entries.size(); i++) {
            var entry = Entries.get(i);
            if (entry != null) {
                var currentEntry = entry;
                while (currentEntry != null) {
                    var next = currentEntry.Next;
                    currentEntry.Next = null;
                    currentEntry = next;
                }
                Entries.set(i, null);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        var set = new HashSet<K>();
        for (var entry : Entries) {
            if (entry != null) {
                set.add(entry.Key);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        var entries = entrySet();
        var size = entries.size();
        var array = new ArrayList<V>(size);
        for (var entry : entries) {
            array.add(entry.getValue());
        }
        return array;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        var set = new HashSet<Map.Entry<K, V>>();
        for (var entry : Entries) {
            if (entry != null) {
                set.add(entry);
            }
        }
        return set;
    }

    private int hashIndex(Object key) {
        return key.hashCode() & (Size - 1);
    }

    private HashSetEntry getEntry(Object key) {
        var index = hashIndex(key);
        var currentEntry = Entries.get(index);
        while (currentEntry != null) {
            if (currentEntry.Key.equals(key)) {
                return currentEntry;
            }
            currentEntry = currentEntry.Next;
        }
        return null;
    }

    private HashSetEntry addEntry(K key, V value) {
        var entry = new HashSetEntry(key, value);
        var index = hashIndex(key);
        var currentEntry = Entries.get(index);
        if (currentEntry == null) {
            Entries.set(index, entry);
            return entry;
        } else if (currentEntry.Key.equals(key)) {
            Entries.set(index, entry);
            entry.Next = currentEntry.Next;
            return entry;
        }

        var next = currentEntry.Next;
        while (next != null) {
            if (next.Key.equals(key)) {
                currentEntry.Next = entry;
                entry.Next = next.Next;
                next.Next = null;
                return entry;
            }
            next = next.Next;
            currentEntry = next;
        }
        currentEntry.Next = entry;
        return entry;
    }

    private HashSetEntry removeEntry(Object key) {
        var index = hashIndex(key);
        if (Entries.get(index) != null) {
            var currentEntry = Entries.get(index);
            if (currentEntry.Key.equals(key)) {
                Entries.set(index, currentEntry.Next);
                currentEntry.Next = null;
                return currentEntry;
            }
            var next = currentEntry.Next;
            while (next != null) {
                if (next.Key.equals(key)) {
                    currentEntry.Next = next.Next;
                    next.Next = null;
                    return next;
                }
                next = next.Next;
                currentEntry = next;
            }
        }
        return null;
    }

    class HashSetEntry implements Map.Entry<K, V> {
        final K Key;
        V Value;
        HashSetEntry Next;

        HashSetEntry(K key, V value) {
            Key = key;
            Value = value;
        }

        @Override
        public K getKey() {
            return Key;
        }

        @Override
        public V getValue() {
            return Value;
        }

        @Override
        public V setValue(V value) {
            return Value = value;
        }
    }
}
