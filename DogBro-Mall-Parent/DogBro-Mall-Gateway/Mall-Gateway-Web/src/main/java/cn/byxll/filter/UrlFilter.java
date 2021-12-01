package cn.byxll.filter;

/**
 * url过滤
 * @author By-Lin
 */
public class UrlFilter {
    /**
     * 不需要拦截的url
     */
    private final static String[] PASS_URLS = new String[]{
            "/api/user/login",
            "/api/user/add"
    };
    /**
     * 校验当前访问的路径是否需要验证权限
     * @param url       url
     * @return          是否需要校验
     */
    public static boolean hasAuthorize(String url) {
        for (String passUrl : PASS_URLS) {
            if(url.equals(passUrl)) {
                return false;
            }
        }
        return true;
    }
}
