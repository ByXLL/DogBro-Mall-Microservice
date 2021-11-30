package cn.byxll.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ReturnOrderItem 实体类
 * @author		By-Lin
 */
@Table(name="tb_return_order_item")
public class ReturnOrderItem implements Serializable{

	/** ID */
	@Id
    @Column(name = "id")
	private String id;

	/** 分类ID */
    @Column(name = "category_id")
	private Integer categoryId;

	/** SPU_ID */
    @Column(name = "spu_id")
	private String spuId;

	/** SKU_ID */
    @Column(name = "sku_id")
	private String skuId;

	/** 订单ID */
    @Column(name = "order_id")
	private String orderId;

	/** 订单明细ID */
    @Column(name = "order_item_id")
	private String orderItemId;

	/** 退货订单ID */
    @Column(name = "return_order_id")
	private String returnOrderId;

	/** 标题 */
    @Column(name = "title")
	private String title;

	/** 单价 */
    @Column(name = "price")
	private Integer price;

	/** 数量 */
    @Column(name = "num")
	private Integer num;

	/** 总金额 */
    @Column(name = "money")
	private Integer money;

	/** 支付金额 */
    @Column(name = "pay_money")
	private Integer payMoney;

	/** 图片地址 */
    @Column(name = "image")
	private String image;

	/** 重量 */
    @Column(name = "weight")
	private Integer weight;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSpuId() {
		return spuId;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getReturnOrderId() {
		return returnOrderId;
	}

	public void setReturnOrderId(String returnOrderId) {
		this.returnOrderId = returnOrderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
