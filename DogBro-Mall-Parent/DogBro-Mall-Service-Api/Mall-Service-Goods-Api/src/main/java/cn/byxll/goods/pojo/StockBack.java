package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 库存回滚操作记录表实体
 * @author By-Lin
 */
@ApiModel(description = "StockBack",value = "StockBack")
@Table(name="tb_stock_back")
public class StockBack implements Serializable{

	@ApiModelProperty(value = "订单id",required = false)
    @Column(name = "order_id")
	private String orderId;

	@ApiModelProperty(value = "SKU的id",required = false)
	@Id
    @Column(name = "sku_id")
	private String skuId;

	@ApiModelProperty(value = "回滚数量",required = false)
    @Column(name = "num")
	private Integer num;

	@ApiModelProperty(value = "回滚状态",required = false)
    @Column(name = "status")
	private String status;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "回滚时间",required = false)
    @Column(name = "back_time")
	private Date backTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
}
