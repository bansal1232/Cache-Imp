package cache;

import java.util.*;

class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class LRUCache<K, V> {
    Map<K, Node<K, V>> keyNodeMap;
    private final int capacity;
    private final Node<K, V> head;
    private final Node<K, V> tail;


    public LRUCache(int size) {
        capacity = size;
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
        keyNodeMap = new HashMap<>();
    }

    public V getValue(K key) {
        if (!keyNodeMap.containsKey(key)) {
            return null;
        }
        Node<K, V> nodeValue = keyNodeMap.get(key);
        removeNode(nodeValue);
        addNodeAtHead(nodeValue);
        return nodeValue.value;
    }

    public void putKey(K key, V value) {
        if (keyNodeMap.containsKey(key)) {
            removeNode(keyNodeMap.get(key));
        } else {
            // evict cache
            if (keyNodeMap.size() == capacity) {
                removeNode(tail.prev);
            }
        }
        addNodeAtHead(new Node<>(key, value));
    }

    private void addNodeAtHead(Node<K, V> node) {
        keyNodeMap.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        if (node == null) {
            return;
        }
        keyNodeMap.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void printAllFromHead() {
        Node<K, V> node = head.next;
        while (node.key != null) {
            node = node.next;
        }
        System.out.println();
    }

    public void printAllFromTail() {
        Node<K, V> node = tail.prev;
        while (node.key != null) {
            node = node.prev;
        }
        System.out.println();
    }
}
