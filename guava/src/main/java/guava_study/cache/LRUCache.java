package guava_study.cache;

/**
 * @author Atom
 */
public interface LRUCache<K, V> {



    void put(K key, V value);

    V get(K key);

    void remote(K key);

    int size();

    void clear();

    int limit();
}
