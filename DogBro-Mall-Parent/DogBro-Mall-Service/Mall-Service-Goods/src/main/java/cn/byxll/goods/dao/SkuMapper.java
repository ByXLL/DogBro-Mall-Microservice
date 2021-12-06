package cn.byxll.goods.dao;
import cn.byxll.goods.pojo.Sku;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Sku的Dao 接口
 * @Author: By-Lin
 */
@Repository
public interface SkuMapper extends Mapper<Sku> {
    /**
     * 减库存
     * @param id        skuId
     * @param num       个数
     * @return          影响行数
     */
    @Update("update tb_sku set num = num-#{num} where id = #{id} and num>=#{num}")
    Integer decrCount(Long id, Integer num);
}
