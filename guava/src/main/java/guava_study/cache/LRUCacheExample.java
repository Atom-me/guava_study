package guava_study.cache;

import guava_study.cache.impl.LinkedListLRUCache;
import guava_study.cache.impl.SoftLRUCache;

import java.util.concurrent.TimeUnit;

/**
 * @author Atom
 * <p>
 * -Xms64M -Xmx128m -XX:+PrintGCDetails
 * <p>
 * 使用LinkedList 或者 hashmap 做的缓存 有一个问题就是可能会发生OOM，可以使用 softReference 优化
 * // 也可以用WeakReference 实现，但是如果你的应用如果频繁发生GC，那你的缓存可能就不起作用了。
 * // 一般缓存都是使用软引用实现
 */
public class LRUCacheExample {

    public static void main(String[] args) throws InterruptedException {
      /*
       final LinkedListLRUCache<String, byte[]> cache = new LinkedListLRUCache(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("the  " + i + " entity is cached .");
        }*/

        // 使用softReference 做优化，但是也有可能发生OOM，
        // 也可以用WeakReference 实现，但是如果你的应用如果频繁发生GC，那你的缓存可能就不起作用了。
        // 一般缓存都是使用软引用实现
        final SoftLRUCache<String, byte[]> cache = new SoftLRUCache(100);
        for (int i = 0; i < 1000; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("the  " + i + " entity is cached .");
        }
    }
}
