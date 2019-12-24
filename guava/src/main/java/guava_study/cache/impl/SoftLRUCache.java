package guava_study.cache.impl;

import guava_study.cache.LRUCache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Atom
 */
public class SoftLRUCache<K, V> implements LRUCache<K, V> {


    // SoftLRUCache 不直接继承 LinkedHashMap 是为了值曝露 LRUCache 本身的方法，屏蔽HashMap的方法。
    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, SoftReference<V>> {

        final private int limit;

        public InternalLRUCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> eldest) {
            return this.size() > limit;
        }
    }

    private final int limit;

    private final InternalLRUCache<K, V> cache;

    public SoftLRUCache(int limit) {
        this.limit = limit;
        this.cache = new InternalLRUCache<>(limit);
    }


    @Override
    public void put(K key, V value) {
        this.cache.put(key, new SoftReference<>(value));
    }

    @Override
    public V get(K key) {
        SoftReference<V> softReference = this.cache.get(key);
        if (null == softReference) return null;
        return softReference.get();// 有可能被GC回收掉。可能会返回null
    }

    @Override
    public void remote(K key) {
        this.cache.remove(key);

    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }
}
