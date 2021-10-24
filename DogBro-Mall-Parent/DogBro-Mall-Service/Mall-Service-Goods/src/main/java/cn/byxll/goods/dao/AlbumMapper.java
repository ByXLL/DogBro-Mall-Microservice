package cn.byxll.goods.dao;

import cn.byxll.goods.pojo.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 相册dao 接口
 * @author By-Lin
 */
@Repository
public interface AlbumMapper extends Mapper<Album> {

}
