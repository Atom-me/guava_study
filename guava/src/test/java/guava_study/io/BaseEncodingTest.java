package guava_study.io;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * 编码，解码 （不是加密解密）
 *
 * @author atom
 */
public class BaseEncodingTest {


    /**
     * 使用guava Base64 编码
     */
    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
    }

    /**
     * 使用guava Base64 解码
     */
    @Test
    public void testBase64Decode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));
    }


    /**
     * 测试自己实现的Base64编码工具和guava的实现 得到的值是否一样
     */
    @Test
    public void testMyBase64Encode() {
        System.out.println(Base64.encode("hello"));
        assertThat(Base64.encode("hello"), equalTo(BaseEncoding.base64().encode("hello".getBytes())));
        assertThat(Base64.encode("Atom"), equalTo(BaseEncoding.base64().encode("Atom".getBytes())));
        assertThat(Base64.encode("sarming"), equalTo(BaseEncoding.base64().encode("sarming".getBytes())));


    }
}
