package datastructures.hash.examples;

import java.util.Objects;

public class MyHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key, table.length);
        Node<K, V> head = table[index];

        Node<K, V> cur = head;
        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        table[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> cur = table[index];

        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                return cur.value;
            }
            cur = cur.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> cur = table[index];
        Node<K, V> prev = null;

        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                if (prev == null) {
                    table[index] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key, table.length);
        Node<K, V> cur = table[index];

        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key, int length) {
        int hash = (key == null) ? 0 : key.hashCode();
        return (hash & 0x7fffffff) % length;
    }

    private boolean equalsKey(K k1, K k2) {
        return Objects.equals(k1, k2);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        Node<K, V>[] newTable = new Node[oldTable.length * 2];

        for (Node<K, V> head : oldTable) {
            Node<K, V> cur = head;

            while (cur != null) {
                Node<K, V> next = cur.next;

                int newIndex = getIndex(cur.key, newTable.length);
                cur.next = newTable[newIndex];
                newTable[newIndex] = cur;

                cur = next;
            }
        }

        table = newTable;
    }
}