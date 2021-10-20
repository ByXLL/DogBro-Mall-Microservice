package cn.byxll.service.impl;

import cn.byxll.dao.ParaMapper;
import cn.byxll.goods.pojo.Para;
import cn.byxll.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 参数
 * @author By-Lin
 */
@Service
public class ParaServiceImpl implements ParaService {
    private final ParaMapper paraMapper;

    public ParaServiceImpl(ParaMapper paraMapper) {
        this.paraMapper = paraMapper;
    }

    /**
     * 添加参数
     * @param para 参数实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> add(Para para) {
        if(para == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误"); }
        int i = paraMapper.insert(para);
        if(i>0) { return new Result<>(true,StatusCode.OK,"添加成功"); }
        return new Result<>(false,StatusCode.ERROR,"添加成功");
    }

    /**
     * 删除参数
     * @param id 参数id
     * @return 响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误"); }
        int i = paraMapper.deleteByPrimaryKey(id);
        if( i>0 ) { return new Result<>(true,StatusCode.OK, "删除成功"); }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /**
     * 修改参数
     * @param para 参数实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> update(Para para) {
        if(para == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误"); }
        int i = paraMapper.updateByPrimaryKey(para);
        if(i>0) { return new Result<>(true, StatusCode.OK, "修改成功"); }
        return new Result<>(false,StatusCode.ERROR, "修改失败");
    }

    /**
     * 通过id查询参数
     * @param id 参数id
     * @return 响应数据
     */
    @Override
    public Result<Para> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误",null); }
        Para para = paraMapper.selectByPrimaryKey(id);
        if( para == null ) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功",para);
    }

    /**
     * 分页查询
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Para>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误", null); }
        PageHelper.startPage(page,pageSize);
        return new Result<>(true, StatusCode.OK, "查询成功",new PageInfo<>(paraMapper.selectAll()));
    }

    /**
     * 分页条件查询
     * @param para     商品参数实体
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Para>> findPagerByParam(Para para, Integer page, Integer pageSize) {
        if(para == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数错误"); }
        PageHelper.startPage(page,pageSize);
        Example example = createExample(para);
        List<Para> paraList = paraMapper.selectByExample(example);
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(paraList));
    }

    /**
     * 通过模板id查询参数
     *
     * @param templateId 模板id
     * @return 响应数据
     */
    @Override
    public Result<List<Para>> findByTemplateId(Integer templateId) {
        if(templateId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        return new Result<>(true,StatusCode.OK,"查询成功", paraMapper.selectByTemplateId(templateId));
    }

    /**
     * 构建查询条件
     * @param para          参数实体
     * @return              查询条件 example
     */
    private Example createExample(Para para) {
        // 自定义条件搜索对象 Example
        Example example = new Example(Para.class);
        // 构建条件构建器
        Example.Criteria criteria = example.createCriteria();
        if(para != null) {
            if(StringUtils.isNotBlank(String.valueOf(para.getId()))) {
                criteria.andEqualTo("id", para.getId());
            }
            if(StringUtils.isNotBlank(para.getName())) {
                criteria.andLike("name", "%" + para.getName() + "%");
            }
            if(StringUtils.isNotBlank(String.valueOf(para.getOptions()))) {
                criteria.andEqualTo("options", para.getOptions());
            }
            if(StringUtils.isNotBlank(String.valueOf(para.getSeq()))) {
                criteria.andEqualTo("seq", para.getSeq());
            }
            if(StringUtils.isNotBlank(String.valueOf(para.getTemplateId()))) {
                criteria.andEqualTo("templateId", para.getTemplateId());
            }
        }
        return example;
    }
}
