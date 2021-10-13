package entity;

/**
 * 返回状态码
 * @author By-Lin
 */
public class StatusCode {
    /** 成功 */
    public static final int OK = 20000;

    /** 失败 */
    public static final int ERROR = 20001;

    /** 参数异常 **/
    public static final int ARGERROR = 20002;

    /** 用户名或密码错误 */
    public static final int LOGINERROR = 20003;

    /** 权限不足 */
    public static final int ACCESSERROR = 20004;

    /** 远程调用失败 */
    public static final int REMOTEERROR = 20005;

    /** 重复操作 */
    public static final int REPERROR = 20006;

    /** 没有对应的抢购数据 */
    public static final int NOTFOUNDERROR = 20007;
}
