package guava_study.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author atom
 */
public class FilesTest {


    private final String SOURCE_FILE = "/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/source.txt";
    private final String TARGET_FILE = "/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/target.txt";

    /**
     * 使用Guava IO拷贝文件
     *
     * @throws IOException
     */
    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(TARGET_FILE);
        Files.copy(new File(SOURCE_FILE), targetFile);
        assertThat(targetFile.exists(), equalTo(true));

        /*
        使用hash算法校验文件拷贝过程中有没有遗漏
         */
        HashCode sourceFileHashCode = Files.asByteSource(new File(SOURCE_FILE)).hash(Hashing.sha256());
        HashCode targetFileHashCode = Files.asByteSource(new File(TARGET_FILE)).hash(Hashing.sha256());
        assertThat(targetFileHashCode.toString(), equalTo(sourceFileHashCode.toString()));
    }


    /**
     * 使用Java NIO 拷贝文件
     *
     * @throws IOException
     */
    @Test
    public void testCopyFileWithJDKNIO2() throws IOException {
        java.nio.file.Files.copy(
                // 这个也可以这么写，
                Paths.get("/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study", "io", "source.txt"),
                Paths.get("/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study", "io", "target.txt"),
                StandardCopyOption.REPLACE_EXISTING
        );
        File targetFile = new File(TARGET_FILE);
        assertThat(targetFile.exists(), equalTo(true));
    }


    /**
     * 使用 guava IO move文件
     *
     * @throws IOException
     */
    @Test
    public void testMoveFile() throws IOException {
        File targetFile = new File(TARGET_FILE);
        try {
            Files.move(new File(SOURCE_FILE), targetFile);
            assertThat(new File(TARGET_FILE).exists(), equalTo(true));
            assertThat(new File(SOURCE_FILE).exists(), equalTo(false));
        } finally {
            Files.move(new File(TARGET_FILE), new File(SOURCE_FILE));
        }
    }


    /**
     * 使用guava IO 读取 文件
     *
     * @throws IOException
     */
    @Test
    public void testToString() throws IOException {
        final String expectedString = "today we wile share the guava io knowledge\n" +
                "bug only for the basic usage, if you wanted to get the more details information\n" +
                "please read the guava document or read source code.\n" +
                "\n" +
                "the guava source code is very cleanly and nice.";

        List<String> list = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result = Joiner.on("\n").join(list);
        assertThat(result, equalTo(expectedString));
    }

    /**
     * 使用guava LineProcess 行处理器 处理文件
     *
     * @throws IOException
     */
    @Test
    public void testToProcessString() throws IOException {

        //[42, 79, 51, 0, 47]
        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {

            private final List<Integer> lengthList = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                if (line.length() == 0) return false; // 看内部源码 会把 0 后面的都截断。
                lengthList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };

        List<Integer> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).readLines(lineProcessor);
        System.out.println(result);

    }

    /**
     * 校验文件签名
     *
     * @throws IOException
     */

    @Test
    public void testFileMD5ORsha() throws IOException {
        File file = new File(SOURCE_FILE);
//        Files.hash(file, Hashing.md5()); // 方法过时了
        HashCode hashCode = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hashCode.toString());
    }

    /**
     * 使用guava 写文件 ，可以支持append的方式，默认是覆盖模式
     *
     * @throws IOException
     */
    @Test
    public void testFileWrite_Wtith_OverWrite() throws IOException {
        final String testPath = "/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/test.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        String content1 = "content 1";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content1);
        String actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actually, equalTo(content1));


        /*
        接着往这个文件写content 2 ，会覆盖原来的文件内容
         */
        String content2 = "content 2";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content2);
        actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actually, equalTo(content2));


    }


    /**
     * 使用guava 写文件 ，可以支持append的方式，默认是覆盖模式
     *
     * @throws IOException
     */
    @Test
    public void testFileWrite_Wtith_Append() throws IOException {
        final String testPath = "/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/test.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        String content1 = "content 1";
        Files.asCharSink(testFile, Charsets.UTF_8, FileWriteMode.APPEND).write(content1);
        String actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actually, equalTo(content1));


        /*
        接着往这个文件写content 2 ，会覆盖原来的文件内容
         */
        String content2 = "content 2";
        Files.asCharSink(testFile, Charsets.UTF_8, FileWriteMode.APPEND).write(content2);
        actually = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actually, equalTo(content1 + content2));

    }


    /**
     * 使用guava IO  touch 文件
     */
    @Test
    public void testTouchFile() throws IOException {
        final String testPath = "/Users/atom/work/workspace/guava_study/guava/src/test/java/guava_study/io/touch.txt";
        File touchFile = new File(testPath);
        touchFile.deleteOnExit();
        Files.touch(touchFile);
        assertThat(touchFile.exists(), equalTo(true));
    }


    /**
     * 使用 JDK 遍历文件
     */
    @Test
    public void testRecursive() {
        List<File> list = new ArrayList<>();
        recusiveList(new File("/Users/atom/work/workspace/guava_study/erp_message_consumer/src/main/java/com/samring/erp_message_consumer"), list);
        list.forEach(System.out::println);

    }

    private void recusiveList(File root, List<File> fileList) {
    /*
    // 递归遍历 一个文件夹下的所有文件和文件夹

       if (root.isHidden())
            return;
        fileList.add(root);
        if (!root.isFile()) {
            File[] files = root.listFiles();
            for (File file : files) {
                recusiveList(file, fileList);
            }
        }*/

        // 只递归遍历文件，过滤掉文件夹
        if (root.isHidden()) return;
        if (root.isFile()) {
            fileList.add(root);
        } else {
            File[] files = root.listFiles();
            for (File file : files) {
                recusiveList(file, fileList);
            }

        }

    }


    /**
     * 使用guava 遍历一个文件夹内所有文件和文件夹（也可以过滤） 正序
     * 顺序的时候内部其实是用 双向队列实现的
     */
    @Test
    public void testTreeFiles_PreOrderTraversal() {
        File root = new File("/Users/atom/work/workspace/guava_study/erp_message_consumer/src/main/java/com/samring/erp_message_consumer");
        FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal(root);
//        FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal(root).filter(File::isFile);
        files.stream().forEach(System.out::println);


    }

    /**
     * 使用guava 遍历一个文件夹内所有文件和文件夹（也可以过滤） 倒序
     * 顺序的时候内部其实是用 双向队列实现的
     */
    @Test
    public void testTreeFiles_PostOrderTraversal() {
        File root = new File("/Users/atom/work/workspace/guava_study/erp_message_consumer/src/main/java/com/samring/erp_message_consumer");
        FluentIterable<File> files = Files.fileTreeTraverser().postOrderTraversal(root);
//        FluentIterable<File> files = Files.fileTreeTraverser().postOrderTraversal(root).filter(File::isFile);
        files.stream().forEach(System.out::println);


    }

    /**
     * 单元测试 测试完成一定要清理文件，否则可能对其他人产生影响
     */
    @After
    public void tearDown() {
        File targetFile = new File(TARGET_FILE);
        if (targetFile.exists())
            targetFile.delete();
    }

}
