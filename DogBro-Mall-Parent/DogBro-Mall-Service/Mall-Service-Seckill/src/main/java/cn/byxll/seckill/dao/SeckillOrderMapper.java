package cn.byxll.seckill.dao;

import cn.byxll.seckill.pojo.SeckillOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * SeckillOrder的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface SeckillOrderMapper extends Mapper<SeckillOrder> {
}
