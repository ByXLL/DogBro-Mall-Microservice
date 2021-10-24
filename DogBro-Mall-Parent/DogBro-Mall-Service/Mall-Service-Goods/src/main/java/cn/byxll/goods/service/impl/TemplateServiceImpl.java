package cn.byxll.goods.service.impl;

import cn.byxll.goods.dao.TemplateMapper;
import cn.byxll.goods.pojo.Template;
import cn.byxll.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 模板service 接口实现类
 * @author By-Lin
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateMapper templateMapper;

    public TemplateServiceImpl(TemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    /**
     * 添加模板
     * @param template  模板实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> add(Template template) {
        if(template == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = templateMapper.insert(template);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "添加失败");
    }

    /**
     * 根据id删除模板
     * @param id        模板id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = templateMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "删除失败");
    }

    /**
     * 修改模板
     * @param template      模板实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Template template) {
        if(template == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = templateMapper.updateByPrimaryKey(template);
        if(i>0) { return new Result<>(true, StatusCode.OK, "修改成功"); }
        return new Result<>(false, StatusCode.ERROR, "修改失败");
    }

    /**
     * 查询所有的模板
     * @return      响应数据
     */
    @Override
    public Result<List<Template>> findAll() {
        List<Template> list = templateMapper.selectAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 通过id查询模板
     * @param id        模板id
     * @return          响应数据
     */
    @Override
    public Result<Template> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Template template = templateMapper.selectByPrimaryKey(id);
        if(template == null) {  return new Result<>(false, StatusCode.ERROR, "查询失败", null); }
        return new Result<>(true, StatusCode.OK, "查询成功", template);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Template>> findPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 生成分页对象
        PageHelper.startPage(page,pageSize);
        List<Template> templateList = templateMapper.selectAll();
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(templateList));
    }

    /**
     * 按条件分页查询
     * @param template      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Template>> findPagerByParam(Template template, Integer page, Integer pageSize) {
        if(template == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 生成分页对象
        PageHelper.startPage(page,pageSize);
        // 搜索数据
        Example example = createExample(template);
        List<Template> templateList = templateMapper.selectByExample(example);
        // 封装PagerInfo
        return new Result<>(true, StatusCode.OK, "查询成功" , new PageInfo<>(templateList));
    }

    /**
     * 构建查询条件
     * @param template      模板实体
     * @return              查询条件 example
     */
    private Example createExample(Template template) {
        // 自定义条件搜索对象 Example
        Example example = new Example(Template.class);
        // 构建条件构建器
        Example.Criteria criteria = example.createCriteria();
        if(template != null) {
            if(StringUtils.isNotBlank(String.valueOf(template.getId()))) {
                criteria.andEqualTo("id", template.getId());
            }
            if(StringUtils.isNotBlank(template.getName())) {
                criteria.andEqualTo("name", template.getName());
            }
            if(StringUtils.isNotBlank(String.valueOf(template.getParaNum()))) {
                criteria.andEqualTo("paraNum", template.getSpecNum());
            }
            if(StringUtils.isNotBlank(String.valueOf(template.getSpecNum()))) {
                criteria.andEqualTo("specNum", template.getSpecNum());
            }
        }
        return example;
    }
}
