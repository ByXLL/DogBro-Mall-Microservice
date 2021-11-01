package cn.byxll.search.service;

import cn.byxll.goods.dto.SearchParam;
import cn.byxll.goods.vo.GoodsListVO;
import entity.Result;

import java.util.Map;

/**
 * SkuService 接口
 * @author By-Lin
 */
public interface SkuService {
    /**
     * 导入sku数据
     * 调用 goods微服务的fegin 查询 符合条件的sku的数据
     * 调用spring data elasticsearch的API 导入到ES中
     * @return      响应数据
     */
    Result<Boolean> importSkuData();


    /**
     * 条件查询
     * @param searchMap     查询对象map
     * @return              响应数据
     */
    Result<GoodsListVO> search(SearchParam searchMap);

}
