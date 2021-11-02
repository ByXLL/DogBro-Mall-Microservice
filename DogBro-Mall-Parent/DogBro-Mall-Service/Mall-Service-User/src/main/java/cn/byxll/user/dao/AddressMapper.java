package cn.byxll.user.dao;
import cn.byxll.user.pojo.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * address mapper 接口类
 * @author By-Lin
 */
@Repository
public interface AddressMapper extends Mapper<Address> {
}
