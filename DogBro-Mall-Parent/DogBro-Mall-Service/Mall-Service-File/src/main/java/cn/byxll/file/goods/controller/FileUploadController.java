package cn.byxll.file.goods.controller;

import cn.byxll.file.entity.FastDFSFile;
import cn.byxll.file.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Value("${fastdfs-server.ip}")
    private String ip;
    @Value("${fastdfs-server.port}")
    private String port;

    @PostMapping("/upload")
    public Result<Object> upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 将文件封装成 fastdfs 文件
        FastDFSFile fastDFSFile = new FastDFSFile(file.getOriginalFilename(), file.getBytes(), StringUtils.getFilenameExtension(file.getOriginalFilename()));
        // 调用FastDFS上传工具类上传文件至FastDFS中
        String[] uploadResultInfo = FastDFSUtil.upload(fastDFSFile);
        String filePath = "http://" + ip + ":" + port + "/" + uploadResultInfo[0] + "/" + uploadResultInfo[1];
        return new Result<>(true, StatusCode.OK,"文件上传成功",filePath);
    }
}
