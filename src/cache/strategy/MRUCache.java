package cache.strategy;

import cache.Cache;
import cache.CacheEntity;
import cache.Node;

import java.util.HashMap;
import java.util.Map;

/*


*/
public class MRUCache<K, V> extends CacheEntity<K,V> implements Cache<K, V> {

    private final Map<K, Node<K, V>> keyNodeMap;

    public MRUCache(int size) {
        super(size, new Node<>(null, null), new Node<>(null, null));
        this.keyNodeMap = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (!keyNodeMap.containsKey(key)) {
            return null;
        }
        Node<K, V> nodeValue = keyNodeMap.get(key);
        removeNode(nodeValue);
        addNodeAtHead(nodeValue);
        printAllFromHead();
        System.out.println(nodeValue.value);

        return nodeValue.value;
    }

    @Override
    public void put(K key, V value) {
        if (keyNodeMap.containsKey(key)) {
            removeNode(keyNodeMap.get(key));
        } else {
            // evict cache
            if (keyNodeMap.size() == capacity) {
                removeNode(head.next);
            }
        }
        addNodeAtHead(new Node<>(key, value));
        printAllFromHead();
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
            System.out.print(node.key + " : " + node.value + ", ");
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
