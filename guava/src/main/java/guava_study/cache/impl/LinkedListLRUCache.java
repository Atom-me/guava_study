package guava_study.cache.impl;

import com.google.common.base.Preconditions;
import guava_study.cache.LRUCache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Atom
 * <p>
 * 使用 LinkedList 实现LRU算法
 * <p>
 * LinkedList 存放 缓存的key, HashMap 存放缓存。
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {

    private final int limit;

    private final LinkedList<K> keys = new LinkedList<>();
    private final Map<K, V> cache = new HashMap<>();

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    /**
     * 缓存的key 单独维护在linkedlist 缓存放在map中
     * 如果 缓存的key 大于等于缓存最大值，就删除key 同时删除缓存。
     * 然后把当前添加的缓存添加到队尾
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        if (keys.size() >= limit) {
            K k = this.keys.removeFirst();
            cache.remove(k);
        }
        this.keys.addLast(key);
        cache.put(key, value);
    }

    /**
     * 先删除，在把这个删除的元素添加到队尾
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        boolean exist = keys.remove(key);
        if (!exist) {
            return null;
        }
        keys.addLast(key);
        return cache.get(key);
    }

    @Override
    public void remote(K key) {

        boolean exist = keys.remove(key);
        if (exist)
            cache.remove(key);

    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void clear() {
        this.keys.clear();
        this.cache.clear();

    }

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (K key : keys) {
            builder.append(key).append("=").append(cache.get(key)).append(";");
        }
        return builder.toString();
    }
}
