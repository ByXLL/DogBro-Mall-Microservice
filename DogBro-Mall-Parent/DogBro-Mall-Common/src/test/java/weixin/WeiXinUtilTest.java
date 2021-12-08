package weixin;

import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具测试类
 * @author By-Lin
 */
public class WeiXinUtilTest {

    @Test
    public void testDemo() throws Exception {
        // 随机字符串
        String str = WXPayUtil.generateNonceStr();
        System.out.println("随机字符串："+str);

        // 将Map转成XML字符串
        HashMap<String,String> map = new HashMap<>(16);
        map.put("name","狗哥");
        map.put("height","180");
        map.put("age","25");
        String mapStr = WXPayUtil.mapToXml(map);
        System.out.println(mapStr);

        // 带签名，将Map转XML字符串
        String mapStr2 = WXPayUtil.generateSignedXml(map, "DogBro");
        System.out.println(mapStr2);

        Map<String, String> toMap = WXPayUtil.xmlToMap(mapStr2);
        System.out.println(toMap);

    }


}
