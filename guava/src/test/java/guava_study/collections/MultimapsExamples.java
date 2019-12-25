package guava_study.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;

/**
 * Multimap 是一键多值的map，相同key的数据不会被覆盖，而是把新增的value放到 value集合里
 *
 * @author Atom
 */
public class MultimapsExamples {

    @Test
    public void test() {
//        LinkedListMultimap<String, String> multimap = new LinkedListMultimap<>();

        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        hashMap.put("2", "2");
        hashMap.put("2", "2");
        System.out.println(hashMap);


        LinkedListMultimap<String, String> linkedListMultimap = LinkedListMultimap.create();
        linkedListMultimap.put("1", "1");
        linkedListMultimap.put("1", "2");
        linkedListMultimap.put("2", "2");
        linkedListMultimap.put("2", "2");
        linkedListMultimap.put("3", "a");
        System.out.println(linkedListMultimap);
        System.out.println(linkedListMultimap.get("1"));
        System.out.println("linkedListMultimap size = " + linkedListMultimap.size());
        System.out.println("linkedListMultimap keyset size = " + linkedListMultimap.keySet().size());

        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("1", "1");
        arrayListMultimap.put("1", "2");
        arrayListMultimap.put("2", "2");
        arrayListMultimap.put("2", "2");
        arrayListMultimap.put("3", "a");
        System.out.println(arrayListMultimap);
        System.out.println(arrayListMultimap.get("1"));
        System.out.println("arrayListMultimap size = " + arrayListMultimap.size());
        System.out.println("arrayListMultimap keyset size = " + arrayListMultimap.keySet().size());

    }
}
