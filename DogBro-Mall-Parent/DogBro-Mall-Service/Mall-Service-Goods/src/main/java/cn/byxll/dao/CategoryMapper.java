package cn.byxll.dao;

import cn.byxll.goods.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 商品分类 dao 接口
 * @author By-Lin
 */
@Repository
public interface CategoryMapper extends Mapper<Category> {
}
