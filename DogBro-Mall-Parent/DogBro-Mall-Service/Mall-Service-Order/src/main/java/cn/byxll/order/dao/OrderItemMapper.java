package cn.byxll.order.dao;

import cn.byxll.order.pojo.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * OrderItem的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface OrderItemMapper extends Mapper<OrderItem> {
}
