package base64;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base64测试类
 * @author By-Lin
 */
public class Base64Test {

    /**
     * base64 加密
     */
    @Test
    public void testEncode() {
        byte[] encode = Base64.getEncoder().encode("DogBro".getBytes(StandardCharsets.UTF_8));
        String encodeStr = new String(encode, StandardCharsets.UTF_8);
        System.out.println("加密后的密文 "+encodeStr);
    }

    @Test
    public void testDecode() {
        String decoderStr = "RG9nQnJv";
        byte[] decode = Base64.getDecoder().decode(decoderStr);
        String encodeStr = new String(decode, StandardCharsets.UTF_8);
        System.out.println("解密后的密文 "+encodeStr);
    }
}
