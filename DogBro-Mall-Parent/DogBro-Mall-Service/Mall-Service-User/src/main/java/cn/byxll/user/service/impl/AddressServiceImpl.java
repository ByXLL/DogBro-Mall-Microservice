package cn.byxll.user.service.impl;

import cn.byxll.user.dao.AddressMapper;
import cn.byxll.user.pojo.Address;
import cn.byxll.user.service.AddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 地址 service 接口实现类
 * @author By-Lin
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    /**
     * 添加用户地址
     * @param address       地址实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> add(Address address) {
        if(address == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        int i = addressMapper.insert(address);
        if(i>0) { return new Result<>(true,StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 通过id删除
     * @param id        主键
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id){
        if(id==null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        int i = addressMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true,StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 修改Address
     * @param address       地址实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Address address){
        if(address==null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        int i = addressMapper.updateByPrimaryKey(address);
        if(i>0) { return new Result<>(true,StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }


    /**
     * 根据ID查询Address
     * @param id        主键
     * @return          响应数据
     */
    @Override
    public Result<Address> findById(Integer id){
        if(id==null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常",null); }
        Address address = addressMapper.selectByPrimaryKey(id);
        if(address == null) { return new Result<>(false,StatusCode.ERROR,"操作失败",null);}
        return new Result<>(true,StatusCode.OK,"操作成功",address);
    }

    /**
     * 查询Address全部数据
     * @return      响应数据
     */
    @Override
    public Result<List<Address>> findAll() {
        return new Result<>(true, StatusCode.OK, "操作成功", addressMapper.selectAll());
    }


    /**
     * Address条件查询
     * @param address       条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Address>> findList(Address address){
        //构建查询条件
        Example example = createExample(address);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", addressMapper.selectByExample(example));
    }

    /**
     * 分页查询
     * @param page      当前页
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @Override
    public Result<PageInfo<Address>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", addressMapper.selectAll());
    }

    /**
     * 条件分页查询
     * @param address   条件实体
     * @param page      当前页
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @Override
    public Result<PageInfo<Address>> findPagerByParam(Address address, Integer page, Integer pageSize) {
        if(address == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(address);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(addressMapper.selectByExample(example)));
    }


    /**
     * 构建查询对象
     * @param address       实体
     * @return              查询对象
     */
    private Example createExample(Address address){
        Example example=new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if(address!=null){
            //
            if(!StringUtils.isEmpty(String.valueOf(address.getId()))){
                criteria.andEqualTo("id",address.getId());
            }
            // 用户名
            if(!StringUtils.isEmpty(address.getUsername())){
                criteria.andLike("username","%"+address.getUsername()+"%");
            }
            // 省
            if(!StringUtils.isEmpty(address.getProvinceid())){
                criteria.andEqualTo("provinceid",address.getProvinceid());
            }
            // 市
            if(!StringUtils.isEmpty(address.getCityid())){
                criteria.andEqualTo("cityid",address.getCityid());
            }
            // 县/区
            if(!StringUtils.isEmpty(address.getAreaid())){
                criteria.andEqualTo("areaid",address.getAreaid());
            }
            // 电话
            if(!StringUtils.isEmpty(address.getPhone())){
                criteria.andEqualTo("phone",address.getPhone());
            }
            // 详细地址
            if(!StringUtils.isEmpty(address.getAddress())){
                criteria.andEqualTo("address",address.getAddress());
            }
            // 联系人
            if(!StringUtils.isEmpty(address.getContact())){
                criteria.andEqualTo("contact",address.getContact());
            }
            // 是否是默认 1默认 0否
            if(!StringUtils.isEmpty(address.getIsDefault())){
                criteria.andEqualTo("isDefault",address.getIsDefault());
            }
            // 别名
            if(!StringUtils.isEmpty(address.getAlias())){
                criteria.andEqualTo("alias",address.getAlias());
            }
        }
        return example;
    }

}
