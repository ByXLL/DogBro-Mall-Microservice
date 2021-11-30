package cn.byxll.order.dao;

import cn.byxll.order.pojo.UndoLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * UndoLog的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface UndoLogMapper extends Mapper<UndoLog> {
}
