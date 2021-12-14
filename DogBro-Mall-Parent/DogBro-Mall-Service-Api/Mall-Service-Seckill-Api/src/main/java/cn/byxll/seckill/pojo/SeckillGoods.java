package cn.byxll.seckill.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SeckillGoods 实体类
 * @author		By-Lin
 */
@Table(name="tb_seckill_goods")
public class SeckillGoods implements Serializable{

	/**  */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/** spu ID */
    @Column(name = "goods_id")
	private Long goodsId;

	/** sku ID */
    @Column(name = "item_id")
	private Long itemId;

	/** 标题 */
    @Column(name = "title")
	private String title;

	/** 商品图片 */
    @Column(name = "small_pic")
	private String smallPic;

	/** 原价格 */
    @Column(name = "price")
	private String price;

	/** 秒杀价格 */
    @Column(name = "cost_price")
	private String costPrice;

	/** 商家ID */
    @Column(name = "seller_id")
	private String sellerId;

	/** 添加日期 */
    @Column(name = "create_time")
	private Date createTime;

	/** 审核日期 */
    @Column(name = "check_time")
	private Date checkTime;

	/** 审核状态，0未审核，1审核通过，2审核不通过 */
    @Column(name = "status")
	private String status;

	/** 开始时间 */
    @Column(name = "start_time")
	private Date startTime;

	/** 结束时间 */
    @Column(name = "end_time")
	private Date endTime;

	/** 秒杀商品数 */
    @Column(name = "num")
	private Integer num;

	/** 剩余库存数 */
    @Column(name = "stock_count")
	private Integer stockCount;

	/** 描述 */
    @Column(name = "introduction")
	private String introduction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
