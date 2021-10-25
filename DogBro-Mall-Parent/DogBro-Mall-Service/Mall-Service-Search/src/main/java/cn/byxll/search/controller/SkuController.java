package cn.byxll.search.controller;

import cn.byxll.search.service.impl.SkuServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * es 微服务暴露出去的控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/search")
public class SkuController {
    private final SkuServiceImpl skuService;

    public SkuController(SkuServiceImpl skuService) {
        this.skuService = skuService;
    }

    /**
     * 导入数据
     * @return      响应结果
     */
    @GetMapping("/import")
    public Result<Boolean> search(){
        skuService.importSkuData();
        return new Result<>(true, StatusCode.OK,"导入数据到索引库中成功！");
    }
}
