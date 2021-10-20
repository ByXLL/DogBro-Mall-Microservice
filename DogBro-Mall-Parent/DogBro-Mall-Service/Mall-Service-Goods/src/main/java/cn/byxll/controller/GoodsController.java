package cn.byxll.controller;

import cn.byxll.goods.dto.Goods;
import cn.byxll.service.impl.GoodsServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsServiceImpl goodsService;

    public GoodsController(GoodsServiceImpl goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 添加商品
     * @param goods     商品信息
     * @return          响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Goods goods) {
        return goodsService.add(goods);
    }
}
