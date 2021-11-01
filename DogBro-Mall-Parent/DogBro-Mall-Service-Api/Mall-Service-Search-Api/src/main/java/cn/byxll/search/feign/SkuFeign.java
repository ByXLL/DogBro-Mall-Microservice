package cn.byxll.search.feign;

import cn.byxll.search.dto.SearchParam;
import cn.byxll.search.vo.GoodsListVO;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * sku feign service api 接口
 * @author By-Lin
 */
@FeignClient(name="search")
@RequestMapping("/search")
public interface SkuFeign {
    /**
     * 查询
     * @param searchMap        商品查询参数dto
     * @return                 响应结果
     */
    @GetMapping("/")
    Result<GoodsListVO> search(@SpringQueryMap SearchParam searchMap);
}
