package cache;

public abstract class CacheEntity<K, V> {

    protected final int capacity;

    protected final Node<K, V> head;

    protected final Node<K, V> tail;

    public CacheEntity(int capacity, Node<K, V> head, Node<K, V> tail) {
        this.capacity = capacity;
        this.head = head;
        this.tail = tail;
        this.head.next = tail;
        this.tail.prev = head;
    }

}
