package cn.byxll.user.controller;

import cn.byxll.user.pojo.Address;
import cn.byxll.user.service.impl.AddressServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址管理 controller 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    /**
     * 添加用户地址
     * @param address       地址实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Address address) {
        return addressService.add(address);
    }

    /**
     * 通过id删除
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping(value = "/delete/{id}")
    public Result<Boolean> delete(@PathVariable Integer id){
        return addressService.delete(id);
    }

    /**
     * 修改Address
     * @param address       地址实体
     * @return              响应数据
     */
    @PostMapping(value="/update")
    public Result<Boolean> update(@RequestBody Address address){
        return addressService.update(address);
    }

    /**
     * 根据ID查询Address
     * @param id        主键
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Address> findById(@PathVariable("id") Integer id){
        return addressService.findById(id);
    }

    /**
     * 查询Address全部数据
     * @return      响应数据
     */
    @GetMapping("/")
    public Result<List<Address>> findAll(){
       return addressService.findAll();
    }

    /**
     * 按条件查询
     * @param address       条件实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Address>> findList(Address address) {
        return addressService.findList(address);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}")
    public Result<PageInfo<Address>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return addressService.findByPager(page,pageSize);
    }

    /**
     * 按条件分页查询
     * @param address       条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Address>> findByPagerParam(@RequestBody Address address, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return addressService.findByPagerParam(address, page, pageSize);
    }
}
