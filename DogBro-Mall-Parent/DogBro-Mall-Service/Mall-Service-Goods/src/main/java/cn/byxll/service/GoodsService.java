package cn.byxll.service;

import cn.byxll.goods.dto.Goods;
import entity.Result;

/**
 * 商品 service 接口
 * @author By-Lin
 */
public interface GoodsService {
    /**
     * 添加商品
     * @param goods     商品dto
     * @return          响应数据
     */
    Result<Boolean> add(Goods goods);
}
