package guava_study.utilites;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * 字符串分割
 *
 * @author atom
 */
public class SplitterTest {


    @Test
    public void testSplitOnSplit() {
        List<String> result = Splitter.on('|').splitToList("hello|world");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));
    }

    @Test
    public void testSplit_on_split_omitEmpty() {
        List<String> result = Splitter.on('|').splitToList("hello|world| | | ");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(5));
        result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
    }

    @Test
    public void testSplit_on_split_omitEmpty_TrimResult() {
        List<String> result = Splitter.on('|').omitEmptyStrings().splitToList("hello | world|| | ");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0), equalTo("hello "));
        assertThat(result.get(1), equalTo(" world"));

        result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));
    }

    @Test
    public void testSplitFixLength() {

        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0), equalTo("aaaa"));
        assertThat(result.get(1), equalTo("bbbb"));
    }

    @Test
    public void testSplitOnSplitLimit() {
        List<String> result = Splitter.on('#').limit(3).splitToList("hello#world#java#google#scala");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));
        assertThat(result.get(2), equalTo("java#google#scala"));
    }

    @Test
    public void testSplitOnPatternString() {
        List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0), equalTo("hello"));
        assertThat(result.get(1), equalTo("world"));
    }
}
