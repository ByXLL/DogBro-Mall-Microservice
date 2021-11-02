package cn.byxll.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 行政区域县区信息表 实体
 * @author By-Lin
 */
@Table(name="tb_areas")
public class Areas implements Serializable{

	/** 区域ID */
	@Id
    @Column(name = "areaid")
	private String areaid;

	/** 区域名称 */
    @Column(name = "area")
	private String area;

	/** 城市ID */
    @Column(name = "cityid")
	private String cityid;



	//get方法
	public String getAreaId() {
		return areaid;
	}

	//set方法
	public void setAreaId(String areaid) {
		this.areaid = areaid;
	}
	//get方法
	public String getArea() {
		return area;
	}

	//set方法
	public void setArea(String area) {
		this.area = area;
	}
	//get方法
	public String getCityId() {
		return cityid;
	}

	//set方法
	public void setCityId(String cityid) {
		this.cityid = cityid;
	}


}
