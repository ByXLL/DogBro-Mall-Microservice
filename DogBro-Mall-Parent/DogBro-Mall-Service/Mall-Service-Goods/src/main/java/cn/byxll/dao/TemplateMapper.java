package cn.byxll.dao;

import cn.byxll.goods.pojo.Template;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 统计分类的规格和参数个数 模板 dao
 * @author By-Lin
 */
@Repository
public interface TemplateMapper extends Mapper<Template> {

}
