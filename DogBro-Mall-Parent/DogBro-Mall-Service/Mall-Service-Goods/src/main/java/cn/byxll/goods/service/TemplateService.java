package cn.byxll.goods.service;

import cn.byxll.goods.pojo.Template;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 模板service 接口
 * @author By-Lin
 */
public interface TemplateService {

    /**
     * 添加模板
     * @param template      模板实体
     * @return              响应数据
     */
    Result<Boolean> add(Template template);

    /**
     * 根据id删除模板
     * @param id        模板id
     * @return          响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改模板
     * @param template      模板实体
     * @return              响应数据
     */
    Result<Boolean> update(Template template);

    /**
     * 查询所有的模板
     * @return      响应数据
     */
    Result<List<Template>> findAll();

    /**
     * 通过id查询模板
     * @param id        模板id
     * @return          响应数据
     */
    Result<Template> findById(Integer id);

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Template>> findPager(Integer page, Integer pageSize);

    /**
     * 按条件分页查询
     * @param template      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Template>> findPagerByParam(Template template, Integer page, Integer pageSize);
}
