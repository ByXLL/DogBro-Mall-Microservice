package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 品牌分类关系数据库
 * @author By-Lin
 */
@ApiModel(description = "CategoryBrand",value = "CategoryBrand")
@Table(name="tb_category_brand")
public class CategoryBrand implements Serializable{

	@Id
    @Column(name = "category_id")
	@ApiModelProperty(value = "分类ID",required = false)
	private Integer categoryId;

	@Id
	@Column(name = "brand_id")
	@ApiModelProperty(value = "品牌ID",required = false)
	private Integer brandId;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

}
