package cn.byxll.service.impl;

import cn.byxll.dao.AlbumMapper;
import cn.byxll.goods.pojo.Album;
import cn.byxll.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     */
    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    /**
     * 删除相册
     * @param id 相册id
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改相册
     * @param album 相册实体
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }

    /**
     * 查询所有相册
     * @return 相册实体集合
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * 通过id查询相册
     * @param id 相册id
     * @return 相册实体
     */
    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 按条件查询相册集合
     *
     * @param album 相册条件实体
     * @return 相册实体集合
     */
    @Override
    public List<Album> findListByParam(Album album) {
        // 构建查询条件按
        Example example = createExample(album);
        // 通过构建的条件查询
        return albumMapper.selectByExample(example);
    }

    /**
     * 相册分页查询
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 分页信息
     */
    @Override
    public PageInfo<Album> findPage(Integer page, Integer pageSize) {
        // 静态分页
        PageHelper.startPage(page,pageSize);
        return new PageInfo<>(albumMapper.selectAll());
    }

    /**
     * 根据条件分页查询
     * @param album    相册条件实体
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 分页信息
     */
    @Override
    public PageInfo<Album> findPageByParam(Album album, Integer page, Integer pageSize) {
        Example example = createExample(album);
        PageHelper.startPage(page,pageSize);
        return new PageInfo<>(albumMapper.selectByExample(example));
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
