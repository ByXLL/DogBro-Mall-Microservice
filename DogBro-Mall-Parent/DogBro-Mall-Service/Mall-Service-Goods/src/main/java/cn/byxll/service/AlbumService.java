package cn.byxll.service;

import cn.byxll.goods.pojo.Album;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 相册service 接口类
 * @author By-Lin
 */
public interface AlbumService {
    /**
     * 新增相册
     * @param album     相册实体
     * @return 响应数据
     */
    Result<Boolean> add(Album album);

    /**
     * 删除相册
     * @param id    相册id
     * @return 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改相册
     * @param album     相册实体
     * @return 响应数据
     */
    Result<Boolean> update(Album album);

    /**
     * 查询所有相册
     * @return      响应数据
     */
    Result<List<Album>> findAll();

    /**
     * 通过id查询相册
     * @param id    相册id
     * @return      响应数据
     */
    Result<Album> findById(Long id);

    /**
     * 按条件查询相册集合
     * @param album     相册条件实体
     * @return          响应数据
     */
    Result<List<Album>> findListByParam(Album album);

    /**
     * 相册分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Album>> findPager(Integer page, Integer pageSize);

    /**
     * 根据条件分页查询
     * @param album         相册条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Album>> findPagerByParam(Album album, Integer page, Integer pageSize);
}
