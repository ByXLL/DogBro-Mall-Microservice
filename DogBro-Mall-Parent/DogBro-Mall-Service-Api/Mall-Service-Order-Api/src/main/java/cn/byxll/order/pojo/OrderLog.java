package cn.byxll.order.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * OrderLog 实体类
 * @author		By-Lin
 */
@Table(name="tb_order_log")
public class OrderLog implements Serializable{

	/** ID */
	@Id
    @Column(name = "id")
	private String id;

	/** 操作员 */
    @Column(name = "operater")
	private String operater;

	/** 操作时间 */
    @Column(name = "operate_time")
	private Date operateTime;

	/** 订单ID */
    @Column(name = "order_id")
	private Long orderId;

	/** 订单状态 */
    @Column(name = "order_status")
	private String orderStatus;

	/** 付款状态 */
    @Column(name = "pay_status")
	private String payStatus;

	/** 发货状态 */
    @Column(name = "consign_status")
	private String consignStatus;

	/** 备注 */
    @Column(name = "remarks")
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getConsignStatus() {
		return consignStatus;
	}

	public void setConsignStatus(String consignStatus) {
		this.consignStatus = consignStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
