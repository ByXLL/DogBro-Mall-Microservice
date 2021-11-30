package cn.byxll.order.service.impl;

import cn.byxll.order.dao.CategoryReportMapper;
import cn.byxll.order.pojo.CategoryReport;
import cn.byxll.order.service.CategoryReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * CategoryReport业务层接口实现类
 * @author By-Lin
 */
@Service
public class CategoryReportServiceImpl implements CategoryReportService {

    private final CategoryReportMapper categoryReportMapper;

    public CategoryReportServiceImpl(CategoryReportMapper categoryReportMapper) {
        this.categoryReportMapper = categoryReportMapper;
    }

    /**
     * 新增CategoryReport
     * @param categoryReport      CategoryReport实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(CategoryReport categoryReport){
        if(categoryReport == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryReportMapper.insert(categoryReport);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Date id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryReportMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改CategoryReport
     * @param categoryReport      CategoryReport实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(CategoryReport categoryReport){
        if(categoryReport == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryReportMapper.updateByPrimaryKey(categoryReport);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询CategoryReport
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<CategoryReport> findById(Date id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        CategoryReport categoryReport = categoryReportMapper.selectByPrimaryKey(id);
        if(categoryReport != null) { return new Result<>(true, StatusCode.OK, "查询成功", categoryReport); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有CategoryReport
     * @return      响应数据
     */
    @Override
    public Result<List<CategoryReport>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", categoryReportMapper.selectAll());
    }

    /**
     * CategoryReport分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<CategoryReport>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(categoryReportMapper.selectAll()));
    }


    /**
     * CategoryReport条件分页查询
     * @param categoryReport      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<CategoryReport>> findPagerByParam(CategoryReport categoryReport, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(categoryReport);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<CategoryReport>(categoryReportMapper.selectByExample(example)));
    }

    /**
     * CategoryReport多条件搜索方法
     * @param categoryReport      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<CategoryReport>> findList(CategoryReport categoryReport){
        if(categoryReport == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(categoryReport);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<CategoryReport>(categoryReportMapper.selectByExample(example)));
    }


    /**
     * CategoryReport构建查询对象
     * @param categoryReport      CategoryReport实体
     * @return              查询对象
     */
    private Example createExample(CategoryReport categoryReport){
        Example example = new Example(CategoryReport.class);
        Example.Criteria criteria = example.createCriteria();
        if(categoryReport!=null){
            // 1级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId1())){
                criteria.andEqualTo("categoryId1",categoryReport.getCategoryId1());
            }
            // 2级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId2())){
                criteria.andEqualTo("categoryId2",categoryReport.getCategoryId2());
            }
            // 3级分类
            if(!StringUtils.isEmpty(categoryReport.getCategoryId3())){
                criteria.andEqualTo("categoryId3",categoryReport.getCategoryId3());
            }
            // 统计日期
            if(!StringUtils.isEmpty(categoryReport.getCountDate())){
                criteria.andEqualTo("countDate",categoryReport.getCountDate());
            }
            // 销售数量
            if(!StringUtils.isEmpty(categoryReport.getNum())){
                criteria.andEqualTo("num",categoryReport.getNum());
            }
            // 销售额
            if(!StringUtils.isEmpty(categoryReport.getMoney())){
                criteria.andEqualTo("money",categoryReport.getMoney());
            }
        }
        return example;
    }

}
