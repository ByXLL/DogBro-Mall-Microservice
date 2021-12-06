package cn.byxll.order.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Order dto 实体类
 * @author		By-Lin
 */
public class OrderDto implements Serializable{
	/** 数量合计 */
	private Integer totalNum;

	/** 金额合计 */
	private Integer totalMoney;

	/** 优惠金额 */
	private Integer preMoney;

	/** 邮费 */
	private Integer postFee;

	/** 实付金额 */
	private Integer payMoney;

	/** 支付类型，1、在线支付、0 货到付款 */
	private String payType;

	/** 买家留言 */
	private String buyerMessage;

	/** 收货人 */
	private String receiverContact;

	/** 收货人手机 */
	private String receiverMobile;

	/** 收货人地址 */
	private String receiverAddress;

	/** 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面 */
	private String sourceType;

	/** 商品id 集合 **/
	private List<Long> skuList;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(Integer preMoney) {
		this.preMoney = preMoney;
	}

	public Integer getPostFee() {
		return postFee;
	}

	public void setPostFee(Integer postFee) {
		this.postFee = postFee;
	}

	public Integer getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getReceiverContact() {
		return receiverContact;
	}

	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public List<Long> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Long> skuList) {
		this.skuList = skuList;
	}
}
