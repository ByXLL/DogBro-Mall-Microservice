package cn.byxll.user.dao;


import cn.byxll.user.pojo.Areas;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 行政区域县区信息 dao 接口类
 * @author By-Lin
 */
@Repository
public interface AreasMapper extends Mapper<Areas> {
}
