package guava_study.utilites;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * 字符串拼接
 */
public class JoinerTest {


    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    private final String targetFileName = "/Users/atom/testDir/guava-joiner-test.txt";

    private final Map<String, String> stringMap = ImmutableMap.of("Hello", "Guava", "Java", "Scala");

    @Test
    public void testJoin_onJoin() {
        String result = Joiner.on('#').join(stringList);
        assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    @Test
    public void testJoin_onJoin_WithNullValue() {
        String result = Joiner.on('#').join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    @Test
    public void testJoin_onJoin_WithNullValueButSkip() {
        String result = Joiner.on('#').skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoin_onJoin_WithNullValue_UseDefaultValue() {
        String result = Joiner.on('#').useForNull("Default").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala#Default"));
    }

    @Test
    public void testJoin_on_append_to_stringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on('#').useForNull("Default").appendTo(builder, stringListWithNullValue);
        assertThat(resultBuilder, sameInstance(builder));
        assertThat(resultBuilder.toString(), equalTo("Google#Guava#Java#Scala#Default"));
        assertThat(builder.toString(), equalTo("Google#Guava#Java#Scala#Default"));
    }


    @Test
    public void testJoin_on_append_to_writer() {
        try (FileWriter writer = new FileWriter(new File(targetFileName))) {
            Joiner.on('#').useForNull("Default").appendTo(writer, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(targetFileName)), equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error");
        }
    }


    @Test
    public void testJoiningByJava8() {
        String result = stringListWithNullValue
                .stream()
                .map(iterm -> iterm == null || iterm.isEmpty() ? "Default" : iterm)
                .collect(Collectors.joining("#"));
        assertThat(result, equalTo("Google#Guava#Java#Scala#Default"));
    }

    @Test
    public void testJoiningByJava8WhithMethodReference() {
        String result = stringListWithNullValue
                .stream()
                .map(this::defaultValue)
                .collect(Collectors.joining("#"));
        assertThat(result, equalTo("Google#Guava#Java#Scala#Default"));
    }

    private String defaultValue(String iterm) {
        return iterm == null || iterm.isEmpty() ? "Default" : iterm;
    }

    @Test
    public void testJoinOnWithMap() {
        String result = Joiner.on('#').withKeyValueSeparator('=').join(stringMap);
        assertThat(result, equalTo("Hello=Guava#Java=Scala"));
    }

}
