package cn.byxll.goods.dao;

import cn.byxll.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 品牌dao 接口
 * 使用通用Mapper 需要继承 tk.mybatis.mapper.common.Mapper
 * @author By-Lin
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类id查询品牌列表
     * @param categoryId        分类id
     * @return                  品牌集合
     */
    @Select("select tb.* from tb_brand as tb, tb_category_brand as tcb where tb.id=tcb.brand_id and tcb.category_id = #{categoryId}")
    List<Brand> selectByCateId(Integer categoryId);
}
