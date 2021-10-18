package cn.byxll.dao;
import cn.byxll.goods.pojo.CategoryBrand;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 品牌与分类关系 的Dao 接口
 * @Author: By-Lin
 */
@Repository
public interface CategoryBrandMapper extends Mapper<CategoryBrand> {
}
