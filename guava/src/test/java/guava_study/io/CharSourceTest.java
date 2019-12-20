package guava_study.io;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CharSourceTest {

    /**
     * Guava CharSource 没有能力从文件直接读取
     *
     * @throws IOException
     */

    @Test
    public void testCharSourceWrap() throws IOException {
        CharSource charSource = CharSource.wrap("i am the charsource");
        String resultAsRead = charSource.read();
        assertThat(resultAsRead, equalTo("i am the charsource"));
        ImmutableList<String> list = charSource.readLines();
        assertThat(list.size(), equalTo(1));
        assertThat(charSource.length(), equalTo(19l));
        assertThat(charSource.isEmpty(), equalTo(false));
        assertThat(charSource.lengthIfKnown().get(), equalTo(19L));
    }

    /**
     * Guava CharSource 拼接
     *
     * @throws IOException
     */

    @Test
    public void testConcat() throws IOException {

        CharSource charSource = CharSource.concat(
                CharSource.wrap("i am the charsource1\n"),
                CharSource.wrap("i am the charsource2")
        );
        System.out.println(charSource.readLines().size());
        charSource.readLines().forEach(System.out::println);
    }
}
