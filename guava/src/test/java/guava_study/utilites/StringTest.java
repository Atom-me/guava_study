package guava_study.utilites;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class StringTest {

    @Test
    public void testStringMethod() {

        assertThat(Strings.emptyToNull(""), nullValue());
        assertThat(Strings.nullToEmpty(null), equalTo(""));
        assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
        assertThat(Strings.commonPrefix("Hello", "Hi"), equalTo("H"));
        assertThat(Strings.commonPrefix("hello", "iiii"), equalTo(""));
        assertThat(Strings.commonSuffix("Hello", "Echo"), equalTo("o"));
        assertThat(Strings.repeat("Atom", 3), equalTo("AtomAtomAtom"));
        assertThat(Strings.isNullOrEmpty(null), equalTo(true));
        assertThat(Strings.isNullOrEmpty(""), equalTo(true));

        assertThat(Strings.padStart("Atom", 3, 'H'), equalTo("Atom"));
        assertThat(Strings.padStart("Atom", 5, 'H'), equalTo("HAtom"));
        assertThat(Strings.padStart("Atom", 6, 'H'), equalTo("HHAtom"));
        assertThat(Strings.padEnd("Atom", 6, 'H'), equalTo("AtomHH"));
    }


    @Test
    public void testCharsets() {
        Charset charset = Charset.forName("UTF-8");
        assertThat(Charsets.UTF_8, equalTo(charset));
    }


    @Test
    public void testCharMatcher() {
        assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
        assertThat(CharMatcher.javaDigit().matches('x'), equalTo(false));

        assertThat(CharMatcher.is('A').countIn("Atom sharing the Google Guava to US"), equalTo(1));
        assertThat(CharMatcher.breakingWhitespace().collapseFrom("   hello Guava      ", '*'), equalTo("*hello*Guava*"));

        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 23413 world"), equalTo("helloworld"));
        assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 23413 world"), equalTo(" 23413 "));

    }


}
