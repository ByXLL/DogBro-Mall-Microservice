package cn.byxll.goods.controller;

import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spec;
import cn.byxll.goods.service.impl.SkuServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/sku")
public class SkuController {
    private final SkuServiceImpl skuService;

    public SkuController(SkuServiceImpl skuService) {
        this.skuService = skuService;
    }

    /**
     * 根据id查询
     * @param id skuId
     * @return   响应数据
     */
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable("id") String id) {
        return skuService.findById(id);
    }

    /**
     * 查询所有规格
     * @return      响应数据
     */
    @GetMapping("/findAll")
    public Result<List<Sku>> findAll() {
        return skuService.findAll();
    }
}
