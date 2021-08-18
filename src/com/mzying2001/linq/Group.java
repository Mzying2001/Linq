package com.mzying2001.linq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group<K, V> implements Iterable<V> {
    private final K _key;
    private final List<V> _list;

    public Group(K key, List<V> list) {
        this._key = key;
        this._list = list;
    }

    public Group(K key, V value) {
        this(key, new ArrayList<>());
        this.add(value);
    }

    @Override
    public Iterator<V> iterator() {
        return this._list.iterator();
    }

    @Override
    public String toString() {
        return "Group{" +
                "_key=" + _key +
                ", _list=" + _list +
                '}';
    }

    public void add(V value) {
        this._list.add(value);
    }

    public K getKey() {
        return this._key;
    }

    public List<V> getList() {
        return this._list;
    }
}
