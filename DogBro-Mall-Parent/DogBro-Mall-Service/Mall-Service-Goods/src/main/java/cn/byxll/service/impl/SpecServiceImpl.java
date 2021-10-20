package cn.byxll.service.impl;

import cn.byxll.dao.SpecMapper;
import cn.byxll.goods.pojo.Spec;
import cn.byxll.service.SpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 规格 service 实现类
 * @author By-Lin
 */
@Service
public class SpecServiceImpl implements SpecService {
    private final SpecMapper specMapper;

    public SpecServiceImpl(SpecMapper specMapper) {
        this.specMapper = specMapper;
    }

    /**
     * 添加规格
     * @param spec 规格实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> add(Spec spec) {
        if(spec == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = specMapper.insert(spec);
        if(i>0) { return new Result<>(true,StatusCode.OK, "添加成功"); }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /**
     * 删除规格
     * @param id 规格id
     * @return 响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = specMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true,StatusCode.OK,"删除失败"); }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /**
     * 修改规格
     *
     * @param spec 规格实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> update(Spec spec) {
        if(spec == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        int i = specMapper.updateByPrimaryKey(spec);
        if(i>0) { return new Result<>(true,StatusCode.OK,"更新成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 通过id查询
     *
     * @param id 规格id
     * @return 响应数据
     */
    @Override
    public Result<Spec> findById(Integer id) {
        if(id == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        Spec spec = specMapper.selectByPrimaryKey(id);
        if(spec == null) { return new Result<>(false,StatusCode.ERROR,"查询失败"); }
        return new Result<>(true,StatusCode.OK,"查询成功", spec);
    }

    /**
     * 查询所有规格
     * @return 响应数据
     */
    @Override
    public Result<List<Spec>> findAll() {
        List<Spec> specList = specMapper.selectAll();
        return new Result<>(true,StatusCode.OK,"查询成功",specList);
    }

    /**
     * 分页查询
     *
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return         响应数据
     */
    @Override
    public Result<PageInfo<Spec>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        PageHelper.startPage(page,pageSize);
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(specMapper.selectAll()));
    }

    /**
     * 分页条件查询
     *
     * @param spec     规格实体
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return         响应数据
     */
    @Override
    public Result<PageInfo<Spec>> findPagerByParam(Spec spec, Integer page, Integer pageSize) {
        if(spec == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        PageHelper.startPage(page, pageSize);
        Example example = createExample(spec);
        List<Spec> specList = specMapper.selectByExample(example);
        return new Result<>(true,StatusCode.OK,"查询成功", new PageInfo<>(specList));
    }

    /**
     * 通过模板id查询规格
     * @param templateId    模板id
     * @return              响应数据
     */
    @Override
    public Result<List<Spec>> findByTemplateId(Integer templateId) {
        if(templateId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        return new Result<>(true,StatusCode.OK,"查询成功", specMapper.selectByTemplateId(templateId));
    }

    /**
     * 构建查询条件
     * @param spec          规格实体
     * @return              查询条件 example
     */
    private Example createExample(Spec spec) {
        // 自定义条件搜索对象 Example
        Example example = new Example(Spec.class);
        // 构建条件构建器
        Example.Criteria criteria = example.createCriteria();
        if(spec != null) {
            if(StringUtils.isNotBlank(String.valueOf(spec.getId()))) {
                criteria.andEqualTo("id", spec.getId());
            }
            if(StringUtils.isNotBlank(spec.getName())) {
                criteria.andLike("name", "%" + spec.getName() + "%");
            }
            if(StringUtils.isNotBlank(String.valueOf(spec.getOptions()))) {
                criteria.andEqualTo("options", spec.getOptions());
            }
            if(StringUtils.isNotBlank(String.valueOf(spec.getSeq()))) {
                criteria.andEqualTo("seq", spec.getSeq());
            }
            if(StringUtils.isNotBlank(String.valueOf(spec.getTemplateId()))) {
                criteria.andEqualTo("templateId", spec.getTemplateId());
            }
        }
        return example;
    }
}
