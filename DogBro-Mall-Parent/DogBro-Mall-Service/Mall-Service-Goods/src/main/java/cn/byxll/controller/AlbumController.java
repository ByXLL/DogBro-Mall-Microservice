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
    public Result<Object> add(@RequestBody Album album) {
        albumService.add(album);
        return new Result<>(true, StatusCode.OK,"添加成功");
    }

    /**
     * 删除相册
     * @param id        相册id
     * @return          响应结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Long id) {
        albumService.delete(id);
        return new Result<>(true,StatusCode.OK,"删除成功");
    }

    /**
     * 修改相册
     * @param album     相册实体
     * @return          响应结果
     */
    @PostMapping("/update")
    public Result<Object> update(@RequestBody Album album) {
        albumService.update(album);
        return new Result<>(true,StatusCode.OK,"修改成功");
    }

    /**
     *查询单个相册
     * @param id        相册id
     * @return          响应结果
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable("id") Long id) {
        return new Result<>(true,StatusCode.OK,"查询成功",albumService.findById(id));
    }

    /**
     * 查询所有相册
     * @return      响应结果
     */
    @GetMapping("/")
    public Result<Object> findAll() {
        List<Album> albumList = albumService.findAll();
        return new Result<>(true,StatusCode.OK,"查询成功",albumList);
    }

    /**
     * 按条件查询
     * @param album     相册实体
     * @return          响应结果
     */
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        List<Album> albumList = albumService.findListByParam(album);
        return new Result<>(true,StatusCode.OK,"查询成功",albumList);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应结果
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Album>> findPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Album> pageInfo = albumService.findPage(page, pageSize);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /**
     * 条件分页查询
     * @param album         条件/相册实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应结果
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<List<Album>> findPageByParam(@RequestBody Album album, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Album> pageInfo = albumService.findPageByParam(album, page, pageSize);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }
}

