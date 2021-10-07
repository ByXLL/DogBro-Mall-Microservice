package cn.byxll.dao;

import cn.byxll.goods.pojo.Brand;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 品牌dao 接口
 * 使用通用Mapper 需要继承 tk.mybatis.mapper.common.Mapper
 * @author By-Lin
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {

}
