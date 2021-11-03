package cn.byxll.user.controller;

import cn.byxll.user.pojo.OauthClientDetails;
import cn.byxll.user.service.impl.OauthClientDetailsServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OauthClientDetails 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/oauthClientDetails")
public class OauthClientDetailsController {

    private final OauthClientDetailsServiceImpl oauthClientDetailsService;

    public OauthClientDetailsController(OauthClientDetailsServiceImpl oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    /**
     * 新增OauthClientDetails数据
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody OauthClientDetails oauthClientDetails){
        return oauthClientDetailsService.add(oauthClientDetails);
    }

    /**
     * 根据ID删除OauthClientDetails数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return oauthClientDetailsService.delete(id);
    }

    /**
     * 修改OauthClientDetails数据
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody OauthClientDetails oauthClientDetails){
        return oauthClientDetailsService.update(oauthClientDetails);
    }

    /**
     * 根据ID查询OauthClientDetails数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<OauthClientDetails> findById(@PathVariable("id") String id){
        return oauthClientDetailsService.findById(id);
    }

    /**
     * 查询OauthClientDetails全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<OauthClientDetails>> findAll(){
        return oauthClientDetailsService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<OauthClientDetails>> findList(@RequestBody OauthClientDetails oauthClientDetails){
        return oauthClientDetailsService.findList(oauthClientDetails);
    }

    /**
     * OauthClientDetails分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OauthClientDetails>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return oauthClientDetailsService.findByPager(page, pageSize);
    }


    /**
     * OauthClientDetails分页条件搜索实现
     * @param oauthClientDetails          OauthClientDetails实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OauthClientDetails>> findPagerByParam(@RequestBody OauthClientDetails oauthClientDetails, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return oauthClientDetailsService.findPagerByParam(oauthClientDetails, page, pageSize);
    }

}
