package cn.byxll.goods.dao;

import cn.byxll.goods.pojo.Para;
import cn.byxll.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 参数 mapper
 * @author By-Lin
 */
@Repository
public interface ParaMapper extends Mapper<Para> {
    /**
     * 通过模板id 分类
     * @param templateId        模板id
     * @return                  规格集合
     */
    @Select("select * from tb_para where template_id = #{templateId}")
    List<Spec> selectByTemplateId(Integer templateId);
}
