package cn.byxll.goods.service;

import cn.byxll.goods.dto.Goods;
import cn.byxll.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 商品 service 接口
 * @author By-Lin
 */
public interface GoodsService {
    /**
     * 保存商品
     * 新增/编辑共用
     * @param goods     商品dto
     * @return          响应数据
     */
    Result<Boolean> saveGoods(Goods goods);

    /**
     * 根据spuId 审核商品
     * @param spuId     spuId
     * @return          响应数据
     */
    Result<Boolean> audit(Long spuId);

    /**
     * 根据spuId 商品下架
     * @param spuId     spuId
     * @return          响应数据
     */
    Result<Boolean> pull(Long spuId);

    /**
     * 根据spuId 集合 批量下架商品
     * @param spuIds     spuId集合
     * @return           响应数据
     */
    Result<Boolean> pullBySpuIds(Long[] spuIds);


    /**
     * 根据spuId 商品上架
     * @param spuId     spuId
     * @return          响应数据
     */
    Result<Boolean> put(Long spuId);

    /**
     * 根据spuId 集合 批量上架商品
     * @param spuIds     spuId集合
     * @return           响应数据
     */
    Result<Boolean> putBySpuIds(Long[] spuIds);

    /**
     * 根据spuId 查询商品信息
     * @param spuId     spuId
     * @return          响应数据
     */
    Result<Goods> findBySpuId(Long spuId);

    /**
     * Spu分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Spu>> findByPager(Integer page, Integer pageSize);

    /**
     * Spu多条件分页查询
     * @param spu      Spu 实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Spu>> findPagerByParam(Spu spu, Integer page, Integer pageSize);


    /**
     * Spu多条件搜索方法
     * @param spu      实体
     * @return         响应数据
     */
    Result<List<Spu>> findListByParam(Spu spu);
}
