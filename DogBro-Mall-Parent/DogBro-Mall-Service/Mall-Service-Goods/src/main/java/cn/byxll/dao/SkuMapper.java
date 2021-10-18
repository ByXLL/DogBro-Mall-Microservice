package cn.byxll.dao;
import cn.byxll.goods.pojo.Sku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Sku的Dao 接口
 * @Author: By-Lin
 */
@Repository
public interface SkuMapper extends Mapper<Sku> {
}
