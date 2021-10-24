package cn.byxll.goods.feign;

import cn.byxll.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SkuFeign 接口
 * @author By-Lin
 */
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {
//    /**
//     * Sku分页查询
//     * @param page          当前页码
//     * @param pageSize      每页大小
//     * @return              响应数据
//     */
//
//    Result<PageInfo<Sku>> findByPager(Integer page, Integer pageSize);
//
//    /**
//     * Sku多条件分页查询
//     * @param sku           Sku 实体
//     * @param page          当前页码
//     * @param pageSize      每页大小
//     * @return              响应数据
//     */
//    Result<PageInfo<Sku>> findPagerByParam(Sku sku, Integer page, Integer pageSize);
//
//
//    /**
//     * Sku多条件搜索方法
//     * @param sku      实体
//     * @return         响应数据
//     */
//    Result<List<Sku>> findListByParam(Sku sku);


    /**
     * 查询所有Sku
     * @return      响应数据
     */
    @GetMapping
    Result<List<Sku>> findAll();
}
