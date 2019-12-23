package guava_study.cache.impl;

/**
 * @author Atom
 */
public class LinkedListLRUExample {

    public static void main(String[] args) {
        LinkedListLRUCache cache = new LinkedListLRUCache(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);

        cache.put("4", "4");
        System.out.println(cache);

        cache.get("3");
        System.out.println(cache);

    }
}
