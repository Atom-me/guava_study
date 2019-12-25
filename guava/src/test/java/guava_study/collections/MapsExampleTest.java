package guava_study.collections;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Atom
 */
public class MapsExampleTest {

    @Test
    public void testCreate(){
        ArrayList<String> valueList = Lists.newArrayList("1", "2", "3");
        ImmutableMap<String, String> map = Maps.uniqueIndex(valueList, v -> v + "_key");
        System.out.println(map);
        Map<String, String> map2 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        System.out.println(map2);
    }

    @Test
    public void testTransform(){
        Map<String, String> map2 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        Map<String, String> newMap = Maps.transformValues(map2, v -> v + "_transform");
        System.out.println(newMap);

    }


}
