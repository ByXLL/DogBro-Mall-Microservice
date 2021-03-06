package cn.byxll.goods.service;

import cn.byxll.goods.pojo.Spu;
import entity.Result;

/**
 * Spu Service接口类
 * @author By-Lin
 */
public interface SpuService {

    /**
     * 新增Spu
     * @param spu 实体
     * @return         响应数据
     */
    Result<Boolean> add(Spu spu);

    /**
     * 删除Spu
     * @param id  主键id
     * @return    响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改Spu数据
     * @param spu  实体
     * @return          响应数据
     */
    Result<Boolean> update(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id    主键id
     * @return      响应数据
     */
    Result<Spu> findById(Long id);

}
