package cn.byxll.dao;

import cn.byxll.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 规格 dao 接口
 * @author By-Lin
 */
@Repository
public interface SpecMapper extends Mapper<Spec> {
    /**
     * 通过模板id 查询规格
     * @param templateId        模板id
     * @return                  规格集合
     */
    @Select("select * from tb_spec where template_id = #{templateId}")
    List<Spec> selectByTemplateId(Integer templateId);
}
