package cn.byxll.controller;

import cn.byxll.goods.pojo.Album;
import cn.byxll.service.impl.AlbumServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 相册控制器
 * @author By-Lin
 */
@RestController
@CrossOrigin
@RequestMapping("/album")
public class AlbumController {
    private final AlbumServiceImpl albumService;

    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }

    /**
     * 添加相册
     * @param album     相册实体
     * @return          响应结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Album album) {
        return albumService.add(album);
    }

    /**
     * 删除相册
     * @param id        相册id
     * @return          响应结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return albumService.delete(id);
    }

    /**
     * 修改相册
     * @param album     相册实体
     * @return          响应结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Album album) {
        return albumService.update(album);
    }

    /**
     *查询单个相册
     * @param id        相册id
     * @return          响应结果
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable("id") Long id) {
        return albumService.findById(id);
    }

    /**
     * 查询所有相册
     * @return      响应结果
     */
    @GetMapping("/")
    public Result<List<Album>> findAll() {
        return albumService.findAll();
    }

    /**
     * 按条件查询
     * @param album     相册实体
     * @return          响应结果
     */
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        return albumService.findListByParam(album);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应结果
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Album>> findPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return albumService.findPager(page, pageSize);
    }

    /**
     * 条件分页查询
     * @param album         条件/相册实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应结果
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Album>> findPagerByParam(@RequestBody Album album, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return albumService.findPagerByParam(album, page, pageSize);
    }
}

