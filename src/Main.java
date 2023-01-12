import cache.LRUCache;

public class Main {
    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(1);
        lruCache.putKey("A", "1");
        lruCache.putKey("B", "2");
        lruCache.putKey("C", "3");
        lruCache.putKey("A", "4");
        lruCache.putKey("D", "9");

        System.out.println(lruCache.getValue("D"));
        System.out.println(lruCache.getValue("B"));
        lruCache.putKey("E", "78");
        System.out.println(lruCache.getValue("E"));    }
}