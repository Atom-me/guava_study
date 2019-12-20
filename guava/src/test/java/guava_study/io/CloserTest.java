package guava_study.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author atom
 */
public class CloserTest {

    public static void main(String[] args) {
        CloserTest test = new CloserTest();
//        test.testDisappearedException();
        test.testDisappearedException2();
    }

    /**
     * 我们用try  catch finally 处理异常，但是如果在finally里面又抛出了异常，上面catch到的异常就会被压住，我们就只能看到finally里面的异常，
     * 不利于我们排查问题。
     * 我们可以使用 Throwable.addSuppressed   的方法解决这个问题。(testDisappearedException2)
     * <p>
     * closer 就是处理这个问题的(testCloser)
     */
    public void testDisappearedException() {
        try {
            Integer.parseInt("hello");
        } catch (NumberFormatException e1) {
            throw e1;
        } finally {
            try {
                int result = 2 / 0;
            } catch (ArithmeticException e2) {
                throw e2;
            }
        }
    }

    public void testDisappearedException2() {
        Throwable t = null;
        try {
            Integer.parseInt("hello");
        } catch (NumberFormatException e1) {
            t = e1;
            throw e1;
        } finally {
            try {
                int result = 2 / 0;
            } catch (ArithmeticException e2) {
                t.addSuppressed(e2);
            }
        }
    }

    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/test111.txt"));
        Closer closer = Closer.create();
        try {
            InputStream inputStream = closer.register(byteSource.openStream());
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }
}

