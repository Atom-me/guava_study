package guava_study.cache;

import guava_study.cache.impl.LinkedListLRUCache;

import java.util.concurrent.TimeUnit;

/**
 * @author Atom
 * <p>
 * -Xms64M -Xmx128m -XX:+PrintGCDetails
 * <p>
 * 使用LinkedList 或者 hashmap 做的缓存 有一个问题就是可能会发生OOM，可以使用softReference 优化
 */
public class LRUCacheExample {

    public static void main(String[] args) throws InterruptedException {
        final LinkedListLRUCache<String, byte[]> cache = new LinkedListLRUCache(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("the  " + i + " entity is cached .");
        }
    }
}
