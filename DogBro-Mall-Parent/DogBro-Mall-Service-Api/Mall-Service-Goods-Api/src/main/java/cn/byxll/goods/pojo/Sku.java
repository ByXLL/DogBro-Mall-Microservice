package cn.byxll.goods.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * sku 实体信息
 * @author By-Lin
 */
@ApiModel(description = "Sku",value = "Sku")
@Table(name="tb_sku")
public class Sku implements Serializable{


	@Id
    @Column(name = "id")
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "商品id",required = false)
	private Long id;

    @Column(name = "sn")
	@ApiModelProperty(value = "商品条码",required = false)
	private String sn;

    @Column(name = "name")
	@ApiModelProperty(value = "SKU名称",required = false)
	private String name;

    @Column(name = "price")
	@ApiModelProperty(value = "价格（分）",required = false)
	private Integer price;

    @Column(name = "num")
	@ApiModelProperty(value = "库存数量",required = false)
	private Integer num;

    @Column(name = "alert_num")
	@ApiModelProperty(value = "库存预警数量",required = false)
	private Integer alertNum;

    @Column(name = "image")
	@ApiModelProperty(value = "商品图片",required = false)
	private String image;

	@ApiModelProperty(value = "商品图片列表",required = false)
    @Column(name = "images")
	private String images;

    @Column(name = "weight")
	@ApiModelProperty(value = "重量（克）",required = false)
	private Integer weight;

    @Column(name = "create_time")
	@ApiModelProperty(value = "创建时间",required = false)
	private Date createTime;

    @Column(name = "update_time")
	@ApiModelProperty(value = "更新时间",required = false)
	private Date updateTime;

    @Column(name = "spu_id")
	@ApiModelProperty(value = "SPUID",required = false)
	private Long spuId;

    @Column(name = "category_id")
	@ApiModelProperty(value = "类目ID",required = false)
	private Integer categoryId;

    @Column(name = "category_name")
	@ApiModelProperty(value = "类目名称",required = false)
	private String categoryName;

    @Column(name = "brand_name")
	@ApiModelProperty(value = "品牌名称",required = false)
	private String brandName;

    @Column(name = "spec")
	@ApiModelProperty(value = "规格",required = false)
	private String spec;


    @Column(name = "sale_num")
	@ApiModelProperty(value = "销量",required = false)
	private Integer saleNum;

    @Column(name = "comment_num")
	@ApiModelProperty(value = "评论数",required = false)
	private Integer commentNum;

    @Column(name = "status")
	@ApiModelProperty(value = "商品状态 1-正常，2-下架，3-删除",required = false)
	private String status;

//	@Column(name = "version")
//	@ApiModelProperty(value = "乐观锁")
//	private Integer version;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getAlertNum() {
		return alertNum;
	}

	public void setAlertNum(Integer alertNum) {
		this.alertNum = alertNum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
