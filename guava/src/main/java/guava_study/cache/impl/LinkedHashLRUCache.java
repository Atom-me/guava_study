package guava_study.cache.impl;

import com.google.common.base.Preconditions;
import guava_study.cache.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * this class is not the thread-save class
 * LRU least recently used 最近最少使用。就是把最近一次使用时间离现在最远的数据删掉。
 * <p>
 * LinkedHashMap 默认的元素顺序是插入的顺序 即put的顺序 即构造函数 accessOrder 参数= false，
 * 如果构造accessOrder设置为true，LinkedHashMap就会按访问的顺序来调整内部顺序。
 * LinkedHashMap 的get()方法 除了返回元素之外还可以把被访问的元素放到链表的底端，这样一来每次顶端的元素就是被remove的元素。
 * <p>
 * LinkedHashMap每次put元素之后都会调用 removeEldestEntry方法，判断是否需要删除最老的元素。默认是false，所以当容量不够的时候，会扩容。
 * 我们可以根据removeEldestEntry方法 扩展一下LinkedHashMap 实现LRU算法。
 * 定义一个缓存的容量 limit ，重写 removeEldestEntry方法 当 map size大于 limit的时候 removeEldestEntry 方法返回true就可以删除链表顶端的元素。
 *
 * @author Atom
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {


    private static class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {

        final private int limit;

        public InternalLRUCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;

    private final InternalLRUCache<K, V> internalLRUCache;

    public LinkedHashLRUCache(int limit) {
        Preconditions.checkArgument(limit > 0, "thie limit must big than zero");
        this.limit = limit;
        this.internalLRUCache = new InternalLRUCache<K, V>(limit);
    }

    @Override
    public void put(K key, V value) {
        this.internalLRUCache.put(key, value);
    }

    @Override
    public V get(K key) {
        return this.internalLRUCache.get(key);
    }

    @Override
    public void remote(K key) {
        this.internalLRUCache.remove(key);
    }

    @Override
    public int size() {
        return this.internalLRUCache.size();
    }

    @Override
    public void clear() {
        this.internalLRUCache.clear();
    }

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public String toString() {
        return internalLRUCache.toString();
    }
}
