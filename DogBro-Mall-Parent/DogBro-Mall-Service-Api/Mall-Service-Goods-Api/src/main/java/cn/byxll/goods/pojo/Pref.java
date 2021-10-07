package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author By-Lin
 */
@ApiModel(description = "Pref",value = "Pref")
@Table(name="tb_pref")
public class Pref implements Serializable{
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID",required = false)
	private Integer id;

    @Column(name = "cate_id")
	@ApiModelProperty(value = "分类ID",required = false)
	private Integer cateId;

    @Column(name = "buy_money")
	@ApiModelProperty(value = "消费金额",required = false)
	private Integer buyMoney;


    @Column(name = "pre_money")
	@ApiModelProperty(value = "优惠金额",required = false)
	private Integer preMoney;

    @Column(name = "start_time")
	@ApiModelProperty(value = "活动开始日期",required = false)
	private Date startTime;

    @Column(name = "end_time")
	@ApiModelProperty(value = "活动截至日期",required = false)
	private Date endTime;

    @Column(name = "type")
	@ApiModelProperty(value = "类型,1:普通订单，2：限时活动",required = false)
	private String type;


    @Column(name = "state")
	@ApiModelProperty(value = "状态,1:有效，0：无效",required = false)
	private String state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Integer getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(Integer buyMoney) {
		this.buyMoney = buyMoney;
	}

	public Integer getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(Integer preMoney) {
		this.preMoney = preMoney;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
