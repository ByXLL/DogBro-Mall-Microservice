package cn.byxll.service;

import cn.byxll.goods.dto.Goods;
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
     * 根据spuId 查询商品信息
     * @param spuId     spuId
     * @return          响应数据
     */
    Result<Goods> findBySpuId(Long spuId);


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
    Result<Boolean> pullBySpuIds(List<Long> spuIds);


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
    Result<Boolean> putBySpuIds(List<Long> spuIds);
}
