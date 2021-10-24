package cn.byxll.goods.service.impl;

import cn.byxll.goods.dao.SpuMapper;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.goods.service.SpuService;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Spu Service接口实现类
 * @Author: By-Lin
 */
@Service
public class SpuServiceImpl implements SpuService {
    private final SpuMapper spuMapper;

    public SpuServiceImpl(SpuMapper spuMapper) {
        this.spuMapper = spuMapper;
    }

    /**
     * 增加Spu
     * @param spu      Spu 实体
     * @return         响应数据
     */
    @Override
    public Result<Boolean> add(Spu spu){
        if(spu == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = spuMapper.insert(spu);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id      Spu 主键id
     * @return        响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = spuMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Spu
     * @param spu       Spu实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> update(Spu spu){
        if(spu == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = spuMapper.updateByPrimaryKey(spu);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Spu
     * @param id        Spu 主键
     * @return          响应数据
     */
    @Override
    public Result<Spu> findById(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if(spu == null) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功", spu);
    }
}
