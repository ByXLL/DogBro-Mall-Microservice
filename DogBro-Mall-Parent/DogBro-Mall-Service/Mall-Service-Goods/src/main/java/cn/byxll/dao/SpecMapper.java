package cn.byxll.dao;

import cn.byxll.goods.pojo.Spec;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 规格 dao 接口
 * @author By-Lin
 */
@Repository
public interface SpecMapper extends Mapper<Spec> {
}
