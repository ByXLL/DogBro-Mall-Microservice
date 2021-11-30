package cn.byxll.order.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Task 实体类
 * @author		By-Lin
 */
@Table(name="tb_task")
public class Task implements Serializable{

	/** 任务id */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**  */
    @Column(name = "create_time")
	private Date createTime;

	/**  */
    @Column(name = "update_time")
	private Date updateTime;

	/**  */
    @Column(name = "delete_time")
	private Date deleteTime;

	/** 任务类型 */
    @Column(name = "task_type")
	private String taskType;

	/** 交换机名称 */
    @Column(name = "mq_exchange")
	private String mqExchange;

	/** routingkey */
    @Column(name = "mq_routingkey")
	private String mqRoutingkey;

	/** 任务请求的内容 */
    @Column(name = "request_body")
	private String requestBody;

	/** 任务状态 */
    @Column(name = "status")
	private String status;

	/** 任务错误信息 */
    @Column(name = "errormsg")
	private String errormsg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getMqExchange() {
		return mqExchange;
	}

	public void setMqExchange(String mqExchange) {
		this.mqExchange = mqExchange;
	}

	public String getMqRoutingkey() {
		return mqRoutingkey;
	}

	public void setMqRoutingkey(String mqRoutingkey) {
		this.mqRoutingkey = mqRoutingkey;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

}
