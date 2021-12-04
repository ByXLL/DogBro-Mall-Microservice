package cn.byxll.user.service;


import cn.byxll.user.pojo.Address;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 用户收货地址 service 接口类
 * @author By-Lin
 */
public interface AddressService {

    /**
     * 添加地址
     * @param address       地址实体
     * @return              响应数据
     */
    Result<Boolean> add(Address address);

    /**
     * 删除地址
     * @param id        地址id
     * @return          响应数据
     */
    Result<Boolean> delete(Integer id);

    /***
     * 修改地址数据
     * @param address       地址实体
     * @return              响应数据
     */
    Result<Boolean> update(Address address);


    /**
     * 根据ID查询
     * @param id            地址id
     * @return              响应数据
     */
    Result<Address> findById(Integer id);

    /**
     * 根据用户名查询收货地址列表
     * @param userName      用户名
     * @return              响应数据
     */
    Result<Address> findByUserName(String userName);

    /**
     * 查询所有Address
     * @return      响应数据
     */
    Result<List<Address>> findAll();

    /**
     * 多条件搜索方法
     * @param address       条件实体
     * @return              响应书画家
     */
    Result<List<Address>> findList(Address address);

    /**
     * 分页查询
     * @param page      当前页
     * @param pageSize  每页大小
     * @return          响应数据
     */
    Result<PageInfo<Address>> findByPager(Integer page, Integer pageSize);

    /**
     * 条件分页查询
     * @param address   条件实体
     * @param page      当前页
     * @param pageSize  每页大小
     * @return          响应数据
     */
    Result<PageInfo<Address>> findPagerByParam(Address address, Integer page, Integer pageSize);
}
