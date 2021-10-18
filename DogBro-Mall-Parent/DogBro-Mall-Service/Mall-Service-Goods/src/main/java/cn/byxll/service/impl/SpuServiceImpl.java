package cn.byxll.service.impl;

import cn.byxll.dao.SpuMapper;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

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

    /**
     * Spu分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Spu>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(spuMapper.selectAll()));
    }

    /**
     * Spu条件+分页查询
     * @param spu           查询条件
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Spu>> findPagerByParam(Spu spu, Integer page, Integer pageSize){
        if(spu == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 静态分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(spu);
        //分页
        PageHelper.startPage(page,pageSize);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(spuMapper.selectByExample(example)));
    }

    /**
     * Spu条件查询
     * @param spu      Spu实体
     * @return              响应数据
     */
    @Override
    public Result<List<Spu>> findListByParam(Spu spu){
        if(spu == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", spuMapper.selectByExample(example));
    }

    /**
     * 查询Spu全部数据
     * @return      响应数据
     */
    @Override
    public Result<List<Spu>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", spuMapper.selectAll());
    }

    /**
     * Spu构建查询对象
     * @param spu      spu实体
     * @return         Example实体
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 主键
            if(!StringUtils.isEmpty(String.valueOf(spu.getId()))){
                criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名
            if(!StringUtils.isEmpty(spu.getName())){
                criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题
            if(!StringUtils.isEmpty(spu.getCaption())){
                criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(String.valueOf(spu.getBrandId()))){
                criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类
            if(!StringUtils.isEmpty(String.valueOf(spu.getCategory1Id()))){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(String.valueOf(spu.getCategory2Id()))){
                criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(String.valueOf(spu.getCategory3Id()))){
                criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID
            if(!StringUtils.isEmpty(String.valueOf(spu.getTemplateId()))){
                criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id
            if(!StringUtils.isEmpty(String.valueOf(spu.getFreightId()))){
                criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片
            if(!StringUtils.isEmpty(spu.getImage())){
                criteria.andEqualTo("image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务
            if(!StringUtils.isEmpty(spu.getSaleService())){
                criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量
            if(!StringUtils.isEmpty(String.valueOf(spu.getSaleNum()))){
                criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(String.valueOf(spu.getCommentNum()))){
                criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否上架
            if(!StringUtils.isEmpty(spu.getIsMarketable())){
                criteria.andEqualTo("isMarketable",spu.getIsMarketable());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否删除
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态
            if(!StringUtils.isEmpty(spu.getStatus())){
                criteria.andEqualTo("status",spu.getStatus());
            }
        }
        return example;
    }
}
