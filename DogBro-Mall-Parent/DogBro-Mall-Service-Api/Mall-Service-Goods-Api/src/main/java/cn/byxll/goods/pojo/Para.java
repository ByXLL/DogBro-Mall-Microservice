package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author By-Lin
 */
@ApiModel(description = "Para",value = "Para")
@Table(name="tb_para")
public class Para implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id",required = false)
    @Column(name = "id")
	private Integer id;

    @Column(name = "name")
	@ApiModelProperty(value = "名称",required = false)
	private String name;


    @Column(name = "options")
	@ApiModelProperty(value = "选项",required = false)
	private String options;

    @Column(name = "seq")
	@ApiModelProperty(value = "排序",required = false)
	private Integer seq;

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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}


}
