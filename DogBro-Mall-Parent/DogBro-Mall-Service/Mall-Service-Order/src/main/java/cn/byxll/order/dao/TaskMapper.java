package cn.byxll.order.dao;

import cn.byxll.order.pojo.Task;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Task的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface TaskMapper extends Mapper<Task> {
}
