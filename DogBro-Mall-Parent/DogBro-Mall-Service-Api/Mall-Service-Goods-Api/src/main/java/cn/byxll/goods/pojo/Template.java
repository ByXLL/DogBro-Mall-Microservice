package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 统计分类的规格和参数个数表
 * @author By-Lin
 */
@ApiModel(description = "Template",value = "Template")
@Table(name="tb_template")
public class Template implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	@ApiModelProperty(value = "模板名称",required = false)
    @Column(name = "name")
	private String name;

	@ApiModelProperty(value = "规格数量",required = false)
    @Column(name = "spec_num")
	private Integer specNum;

	@ApiModelProperty(value = "参数数量",required = false)
    @Column(name = "para_num")
	private Integer paraNum;

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

	public Integer getSpecNum() {
		return specNum;
	}

	public void setSpecNum(Integer specNum) {
		this.specNum = specNum;
	}

	public Integer getParaNum() {
		return paraNum;
	}

	public void setParaNum(Integer paraNum) {
		this.paraNum = paraNum;
	}
}
