package cn.byxll.order.service;

import cn.byxll.order.pojo.CategoryReport;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.Date;
import java.util.List;

/**
 * CategoryReport业务层接口类
 * @author @By-Lin
 */
public interface CategoryReportService {

    /**
     * 新增CategoryReport
     * @param   categoryReport      CategoryReport实体
     * @return                 响应数据
     */
    Result<Boolean> add(CategoryReport categoryReport);

    /**
     * 通过组件删除CategoryReport
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Date id);

    /**
     * 修改CategoryReport数据
     * @param categoryReport      CategoryReport实体
     * @return              响应数据
     */
    Result<Boolean> update(CategoryReport categoryReport);

    /**
     * 根据ID查询CategoryReport
     * @param id        主键id
     * @return          响应数据
     */
    Result<CategoryReport> findById(Date id);

    /**
     * 查询所有CategoryReport
     * @return          响应数据
     */
    Result<List<CategoryReport>> findAll();

    /**
     * CategoryReport分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<CategoryReport>> findByPager(Integer page, Integer pageSize);

    /**
     * CategoryReport条件分页查询
     * @param categoryReport      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<CategoryReport>> findPagerByParam(CategoryReport categoryReport, Integer page, Integer pageSize);
    

    /**
     * CategoryReport多条件搜索方法
     * @param categoryReport      条件实体
     * @return              响应数据
     */
    Result<List<CategoryReport>> findList(CategoryReport categoryReport);
}
