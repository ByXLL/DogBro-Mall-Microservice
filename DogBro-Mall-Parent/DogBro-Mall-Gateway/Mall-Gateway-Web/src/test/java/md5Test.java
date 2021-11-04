import cn.byxll.utils.Md5Util;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * @author By-Lin
 */
public class md5Test {
    @Test
    public void test() {
            String md5 = Md5Util.getMD5String("Aa123456");
            System.out.println(md5);

    }
}
