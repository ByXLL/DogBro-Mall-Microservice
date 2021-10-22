package cn.byxll.controller;

import cn.byxll.goods.dto.Goods;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.service.impl.GoodsServiceImpl;
import com.github.pagehelper.PageInfo;
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
    public Result<Boolean> pullBySpuIds(@RequestBody Long[] spuIds) {
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
    public Result<Boolean> putBySpuIds(@RequestBody Long[] spuIds) {
        return goodsService.putBySpuIds(spuIds);
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
     * 分页查询商品列表
     * @param page      当前页码
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Spu>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return goodsService.findByPager(page, pageSize);
    }

    /**
     * 按条件分页查询商品列表
     * @param spu       spu实体
     * @param page      当前页码
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Spu>> findPagerByParam(@RequestBody Spu spu, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return goodsService.findPagerByParam(spu, page, pageSize);
    }

    /**
     * 条件查询
     * @param spu       spu实体
     * @return          响应数据
     */
    @PostMapping("/search")
    public Result<List<Spu>> findListByParam(@RequestBody Spu spu) {
        return goodsService.findListByParam(spu);
    }
}
