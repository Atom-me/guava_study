package guava_study.utilites;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * 断言
 */
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            checkNotNull_With_Message(null);
        } catch (Exception e) {
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("this list should not be null"));
        }
    }

    @Test
    public void testCheckNotNull_With_FormatMessage() {
        try {
            checkNotNull_With_FormatMessage(null);
        } catch (Exception e) {
            assertThat(e, instanceOf(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("this list should not be null and the size must > 2"));
        }
    }


    private void checkNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);
    }

    private void checkNotNull_With_Message(final List<String> list) {
        Preconditions.checkNotNull(list, "this list should not be null");
    }

    /**
     * 支持消息格式化
     *
     * @param list
     */
    private void checkNotNull_With_FormatMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "this list should not be null and the size must > %s", 2);
    }
}
