package cn.byxll.service.impl;

import cn.byxll.dao.AlbumMapper;
import cn.byxll.goods.pojo.Album;
import cn.byxll.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 相册service 实现类
 * @author By-Lin
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumMapper albumMapper;

    public AlbumServiceImpl(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    /**
     * 新增相册
     * @param album 相册实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> add(Album album) {
        if(album == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = albumMapper.insert(album);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除相册
     * @param id 相册id
     * @return  响应数据
     */
    @Override
    public Result<Boolean> delete(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = albumMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改相册
     * @param album 响应数据
     */
    @Override
    public Result<Boolean> update(Album album) {
        if(album == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = albumMapper.updateByPrimaryKey(album);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 查询所有相册
     * @return 响应数据
     */
    @Override
    public Result<List<Album>> findAll() {
        List<Album> albumList = albumMapper.selectAll();
        return new Result<>(true, StatusCode.OK,"查询成功", albumList);
    }

    /**
     * 通过id查询相册
     * @param id    相册id
     * @return      响应数据
     */
    @Override
    public Result<Album> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Album album = albumMapper.selectByPrimaryKey(id);
        if(album == null) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功", album);
    }

    /**
     * 按条件查询相册集合
     * @param album     相册条件实体
     * @return          响应数据
     */
    @Override
    public Result<List<Album>> findListByParam(Album album) {
        if(album == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        // 构建查询条件按
        Example example = createExample(album);
        // 通过构建的条件查询
        List<Album> albumList = albumMapper.selectByExample(example);
        return new Result<>(true, StatusCode.OK, "查询成功", albumList);
    }

    /**
     * 相册分页查询
     * @param page      当前页码
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @Override
    public Result<PageInfo<Album>> findPage(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        // 静态分页
        PageHelper.startPage(page,pageSize);
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(albumMapper.selectAll()));
    }

    /**
     * 根据条件分页查询
     * @param album     相册条件实体
     * @param page      当前页码
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @Override
    public Result<PageInfo<Album>> findPageByParam(Album album, Integer page, Integer pageSize) {
        if(album == null || page == null || pageSize == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        Example example = createExample(album);
        PageHelper.startPage(page,pageSize);
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(albumMapper.selectByExample(example)));
    }

    /**
     * 构建查询条件
     * @param album     相册参数实体
     * @return          查询条件
     */
    private Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if(album != null) {
            if (StringUtils.isNotBlank(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            if(StringUtils.isNotBlank(String.valueOf(album.getId()))) {
                criteria.andEqualTo("id",album.getId());
            }
            if(StringUtils.isNotBlank(album.getImage())) {
                criteria.andEqualTo("image",album.getImage());
            }
            if(StringUtils.isNotBlank(album.getImageItems())) {
                criteria.andEqualTo("imageItems",album.getImageItems());
            }
        }
        return example;
    }
}
