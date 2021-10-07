package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 商品分类表
 * @author By-Lin
 */

@Table(name="tb_category")
@ApiModel(description = "Category",value = "Category")
public class Category implements Serializable {

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "分类ID",required = false)
	private Integer id;

    @Column(name = "name")
	@ApiModelProperty(value = "分类名称",required = false)
	private String name;

    @Column(name = "goods_num")
	@ApiModelProperty(value = "商品数量",required = false)
	private Integer goodsNum;

    @Column(name = "is_show")
	@ApiModelProperty(value = "是否显示",required = false)
	private String isShow;

    @Column(name = "is_menu")
	@ApiModelProperty(value = "是否导航",required = false)
	private String isMenu;

    @Column(name = "seq")
	@ApiModelProperty(value = "排序",required = false)
	private Integer seq;

    @Column(name = "parent_id")
	@ApiModelProperty(value = "上级ID",required = false)
	private Integer parentId;

    @Column(name = "template_id")
	@ApiModelProperty(value = "模板ID",required = false)
	private Integer templateId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}
