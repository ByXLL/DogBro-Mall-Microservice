package cn.byxll.search.controller;

import cn.byxll.goods.dto.SearchParam;
import cn.byxll.search.service.SkuService;
import cn.byxll.search.service.impl.SkuServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * es 微服务暴露出去的控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/search")
public class SearchController {
    private final SkuServiceImpl skuService;

    public SearchController(SkuServiceImpl skuService) {
        this.skuService = skuService;
    }

    /**
     * 导入数据
     * @return      响应结果
     */
    @RequestMapping("/import")
    public Result<Boolean> importSkuData(){
        return skuService.importSkuData();
    }

    /**
     * 查询
     * @return      响应结果
     */
    @RequestMapping("/")
    public Result<Map<String,Object>> search(SearchParam searchMap){
        return skuService.search(searchMap);
    }
}
