package cn.byxll.user.dao;

import cn.byxll.user.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * User的Dao 接口类
 * @author  By-Lin
 */
@Repository
public interface UserMapper extends Mapper<User> {
}
