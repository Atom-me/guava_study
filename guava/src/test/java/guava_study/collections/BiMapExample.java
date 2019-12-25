package guava_study.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @author Atom
 * BiMap 是根据Value的值确定唯一性的
 */
public class BiMapExample {

    @Test
    public void testPut() {

        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("1", "2");
//        biMap.put("2", "2");// 在放一个value为2的就会报错
        biMap.put("1", "3"); // 会把 biMap.put("1", "2"); 覆盖
        biMap.put("2", "4"); //
        System.out.println(biMap);
    }

    @Test
    public void testForcePut() {
        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.forcePut("2", "2");// 强制放入
        System.out.println(biMap);
    }

    @Test
    public void testBiMapInverse() {
        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("2", "3");
        biMap.put("3", "4");
        System.out.println(biMap);
        BiMap<Object, Object> inverse = biMap.inverse();
        System.out.println(biMap);
        System.out.println(inverse);
    }
}
