package cn.byxll.goods.controller;

import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.goods.service.impl.SpuServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * spu 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/spu")
public class SpuController {
    private final SpuServiceImpl spuService;

    public SpuController(SpuServiceImpl spuService) {
        this.spuService = spuService;
    }

    /**
     * 根据id查询
     * @param id spuId
     * @return   响应数据
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable("id") Long id) {
        return spuService.findById(id);
    }


}
