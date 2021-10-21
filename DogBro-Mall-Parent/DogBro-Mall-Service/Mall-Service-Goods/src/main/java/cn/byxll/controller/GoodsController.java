package cn.byxll.controller;

import cn.byxll.goods.dto.Goods;
import cn.byxll.service.impl.GoodsServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 保存商品
     * 添加/编辑商品
     * @param goods     商品信息
     * @return          响应数据
     */
    @PostMapping("/save")
    public Result<Boolean> saveGoods(@RequestBody(required = true) Goods goods) {
        return goodsService.saveGoods(goods);
    }

    /**
     * 通过spuId查询商品信息
     * @param spuId     spuId
     * @return          响应信息
     */
    @GetMapping("/search/{spuId}")
    public Result<Goods> findBySpuId(@PathVariable("spuId") Long spuId) {
        return goodsService.findBySpuId(spuId);
    }

    /**
     * 通过spuId审核商品
     * @param spuId     spuId
     * @return          响应信息
     */
    @PostMapping("/audit/{spuId}")
    public Result<Boolean> audit(@PathVariable("spuId") Long spuId) {
        return goodsService.audit(spuId);
    }

    /**
     * 通过spuId下架商品
     * @param spuId     spuId
     * @return          响应数据
     */
    @PostMapping("/pull/{spuId}")
    public Result<Boolean> pull(@PathVariable("spuId") Long spuId) {
        return goodsService.pull(spuId);
    }

    /**
     * 通过spuId批量下架商品
     * @return          响应数据
     */
    @PostMapping("/pullBySpuIds")
    public Result<Boolean> pullBySpuIds(@RequestBody List<Long> spuIds) {
        return goodsService.pullBySpuIds(spuIds);
    }


    /**
     * 通过spuId上架商品
     * @param spuId     spuId
     * @return          响应数据
     */
    @PostMapping("/put/{spuId}")
    public Result<Boolean> put(@PathVariable("spuId") Long spuId) {
        return goodsService.put(spuId);
    }

    /**
     * 通过spuId批量上架商品
     * @return          响应数据
     */
    @PostMapping("/putBySpuIds")
    public Result<Boolean> putBySpuIds(@RequestBody List<Long> spuIds) {
        return goodsService.putBySpuIds(spuIds);
    }

}
