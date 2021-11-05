import cn.byxll.utils.PasswordUtil;
import org.junit.Test;

/**
 * @author By-Lin
 */
public class md5Test {
    @Test
    public void test() {
            String md5 = PasswordUtil.getMd5Password("Aa123456");
            System.out.println(md5);
    }

    @Test
    public void checkPassword() {
        boolean verifyPassword = PasswordUtil.verifyPassword("Aa123456", "fbaa5fb0ff15a0b7e650a99a00908efc");
        System.out.println(verifyPassword);
    }
}
