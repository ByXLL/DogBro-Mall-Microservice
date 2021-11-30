package cn.byxll.order.dao;

import cn.byxll.order.pojo.OrderLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * OrderLog的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface OrderLogMapper extends Mapper<OrderLog> {
}
