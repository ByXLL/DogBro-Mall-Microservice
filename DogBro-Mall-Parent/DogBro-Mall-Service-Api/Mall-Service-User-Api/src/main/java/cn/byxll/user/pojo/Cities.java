package cn.byxll.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 行政区地级市州信息
 * @author By-Lin
 */
@Table(name="tb_cities")
public class Cities implements Serializable{

	/** 城市ID */
	@Id
    @Column(name = "cityid")
	private String cityid;

	/** 城市名称 */
    @Column(name = "city")
	private String city;

	/** 省份ID */
    @Column(name = "provinceid")
	private String provinceid;



	//get方法
	public String getCityId() {
		return cityid;
	}

	//set方法
	public void setCityId(String cityid) {
		this.cityid = cityid;
	}
	//get方法
	public String getCity() {
		return city;
	}

	//set方法
	public void setCity(String city) {
		this.city = city;
	}
	//get方法
	public String getProvinceId() {
		return provinceid;
	}

	//set方法
	public void setProvinceId(String provinceid) {
		this.provinceid = provinceid;
	}


}
