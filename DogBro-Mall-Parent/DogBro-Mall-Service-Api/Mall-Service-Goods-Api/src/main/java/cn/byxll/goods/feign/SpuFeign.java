package cn.byxll.goods.feign;

import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spu;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SpuFeign 接口
 * FeignClient 用于声明调用的微服务
 * 注意，这里面声明的每一个方法，必须在该微服务中controller和service中有对应的实现，否则将404
 * @author By-Lin
 */
@FeignClient(value = "goods")
@RequestMapping("/spu")
public interface SpuFeign {

    /**
     * 根据id查询
     * @param id spuId
     * @return   响应数据
     */
    @GetMapping("/{id}")
    Result<Spu> findById(@PathVariable("id") Long id);

}
