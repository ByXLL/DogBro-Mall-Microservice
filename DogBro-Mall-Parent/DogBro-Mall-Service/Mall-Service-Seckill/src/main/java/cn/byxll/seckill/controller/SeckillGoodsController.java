package cn.byxll.seckill.controller;

import cn.byxll.seckill.pojo.SeckillGoods;
import cn.byxll.seckill.service.impl.SeckillGoodsServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SeckillGoods 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

    private final SeckillGoodsServiceImpl seckillGoodsService;

    public SeckillGoodsController(SeckillGoodsServiceImpl seckillGoodsService) {
        this.seckillGoodsService = seckillGoodsService;
    }

    /**
     * 新增SeckillGoods数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SeckillGoods seckillGoods){
        return seckillGoodsService.add(seckillGoods);
    }

    /**
     * 根据ID删除SeckillGoods数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Long id){
        return seckillGoodsService.delete(id);
    }

    /**
     * 修改SeckillGoods数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SeckillGoods seckillGoods){
        return seckillGoodsService.update(seckillGoods);
    }

    /**
     * 根据ID查询SeckillGoods数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<SeckillGoods> findById(@PathVariable("id") Long id){
        return seckillGoodsService.findById(id);
    }

    /**
     * 查询SeckillGoods全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<SeckillGoods>> findAll(){
        return seckillGoodsService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<SeckillGoods>> findList(@RequestBody SeckillGoods seckillGoods){
        return seckillGoodsService.findList(seckillGoods);
    }

    /**
     * SeckillGoods分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping("/search/{page}/{pageSize}" )
    public Result<PageInfo<SeckillGoods>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return seckillGoodsService.findByPager(page, pageSize);
    }


    /**
     * SeckillGoods分页条件搜索实现
     * @param seckillGoods          SeckillGoods实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping("/search/{page}/{pageSize}" )
    public Result<PageInfo<SeckillGoods>> findPagerByParam(@RequestBody SeckillGoods seckillGoods, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return seckillGoodsService.findPagerByParam(seckillGoods, page, pageSize);
    }

}
