package cn.byxll.order.dao;

import cn.byxll.order.pojo.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Order的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface OrderMapper extends Mapper<Order> {
}
