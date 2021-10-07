package cn.byxll.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 商品相册表
 * @author By-Lin
 */
@Table(name="tb_album")
public class Album implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

    @Column(name = "title")
	@ApiModelProperty(value = "相册名称")
	private String title;

    @Column(name = "image")
	@ApiModelProperty(value = "相册封面")
	private String image;

    @Column(name = "image_items")
	@ApiModelProperty(value = "图片列表")
	private String imageItems;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public String getImageItems() {
		return imageItems;
	}

	public void setImageItems(String imageItems) {
		this.imageItems = imageItems;
	}
}
