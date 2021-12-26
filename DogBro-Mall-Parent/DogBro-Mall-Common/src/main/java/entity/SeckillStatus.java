package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户排队抢单信息封装
 * @author By-Lin
 */
public class SeckillStatus implements Serializable {

    /**
     * 秒杀用户名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 秒杀状态  1:排队中，2:秒杀等待支付,3:支付超时，4:秒杀失败,5:支付完成
     */
    private Integer status;

    /**
     * 秒杀的商品ID
     */
    private Long goodsId;

    /**
     * 应付金额
     */
    private Float money;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 时间段
     */
    private String time;

    public SeckillStatus() {
    }

    public SeckillStatus(String userName, Date createTime, Integer status, Long goodsId, String time) {
        this.userName = userName;
        this.createTime = createTime;
        this.status = status;
        this.goodsId = goodsId;
        this.time = time;
    }

    public SeckillStatus(String userName, Date createTime, Integer status, Long goodsId, Float money, Long orderId, String time) {
        this.userName = userName;
        this.createTime = createTime;
        this.status = status;
        this.goodsId = goodsId;
        this.money = money;
        this.orderId = orderId;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}