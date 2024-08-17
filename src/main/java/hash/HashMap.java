package hash;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMap<K ,V> implements Map<K, V> {
    private final int INITIAL_CAPACITY = 10;
    private Node<K, V>[] buckets;
    private int size;

    public HashMap() {
        buckets = new Node[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return key.hashCode() % buckets.length;
    }
    @Override
    public boolean containsKey(Object key) {
        if (isEmpty()) {
            return false;
        }
        Node<K, V> target = buckets[hash((K) key)];
        while (target != null) {
            if (target.key.equals(key)) {
                return true;
            }
            target = target.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> target : buckets) {

        }
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection<V> values() {
        return List.of();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }

}
