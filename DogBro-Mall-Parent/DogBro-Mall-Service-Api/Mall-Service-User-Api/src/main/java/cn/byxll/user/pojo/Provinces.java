package cn.byxll.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name="tb_provinces")
public class Provinces implements Serializable{

	/**
	 * 省份id
	 */
	@Id
    @Column(name = "provinceid")
	private String provinceid;

	/**
	 * 省份名称
	 */
    @Column(name = "province")
	private String province;



	//get方法
	public String getProvinceId() {
		return provinceid;
	}

	//set方法
	public void setProvinceId(String provinceid) {
		this.provinceid = provinceid;
	}
	//get方法
	public String getProvince() {
		return province;
	}

	//set方法
	public void setProvince(String province) {
		this.province = province;
	}


}
