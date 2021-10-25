package cn.byxll.goods.feign;

import cn.byxll.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * SkuFeign 接口
 * FeignClient 用于声明调用的微服务
 * 注意，这里面声明的每一个方法，必须在该微服务中controller和service中有对应的实现，否则将404
 * @author By-Lin
 */
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {
    /**
     * 查询所有Sku
     * @return      响应数据
     */
    @GetMapping("/findAll")
    Result<List<Sku>> findAll();



}
