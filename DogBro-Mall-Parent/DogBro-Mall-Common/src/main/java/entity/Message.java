package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * MQ消息封装
 * @author By-Lin
 */
public class Message implements Serializable{

    /**
     * 执行的操作:
     *  1：增加，2：修改,3：删除
     */

    private int code;

    /**
     * 数据
     */
    private Object content;

    /**
     * 发送的 routeKey
     */

    @JSONField(serialize = false)
    private String routeKey;

    /**
     * 交换机
     */
    @JSONField(serialize = false)
    private String exechange;

    public Message() {
    }

    public Message(int code, Object content) {
        this.code = code;
        this.content = content;
    }

    public Message(int code, Object content, String routekey, String exechange) {
        this.code = code;
        this.content = content;
        this.routeKey = routekey;
        this.exechange = exechange;
    }

    public String getRoutekey() {
        return routeKey;
    }

    public void setRoutekey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getExechange() {
        return exechange;
    }

    public void setExechange(String exechange) {
        this.exechange = exechange;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
