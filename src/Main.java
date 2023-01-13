import cache.Cache;
import cache.strategy.CacheFactory;
import cache.strategy.CacheTypes;
import cache.strategy.LRUCache;

public class Main {
    public static void main(String[] args) {
        assert false : "JKJWKER";
//        LRUTesting();
        MRUTesting();
    }

    private static void LRUTesting() {
        Cache<String, String> lruCache = new LRUCache<>(1);
        lruCache.put("A", "1");
        lruCache.put("B", "2");
        lruCache.put("C", "3");
        lruCache.put("A", "4");
        lruCache.put("D", "9");

        System.out.println(lruCache.get("D"));
        System.out.println(lruCache.get("B"));
        lruCache.put("E", "78");
        System.out.println(lruCache.get("E"));
    }

    private static void MRUTesting() {
        Cache<Integer, Integer> cache = CacheFactory.getCacheType(CacheTypes.MRU, 3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 1
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 2
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}