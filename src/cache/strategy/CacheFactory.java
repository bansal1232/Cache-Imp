package cache.strategy;

import cache.Cache;

public class CacheFactory<K, V> {
    public static <K, V> Cache<K, V> getCacheType(CacheTypes cacheTypes, int size) {
        if (cacheTypes.equals(CacheTypes.LRU))
            return new LRUCache<>(size);

        else if (cacheTypes.equals(CacheTypes.MRU))
            return new MRUCache<>(size);

        return new LRUCache<>(size);
    }
}
