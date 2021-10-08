package cn.byxll.util;

import cn.byxll.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * fastdfs 文件上传插件
 * @author By-Lin
 */
public class FastDFSUtil {

    // 加载Tracker配置的链接信息
    static {
        try {
            String fileName = new ClassPathResource("fastdfs_client.conf").getPath();
            ClientGlobal.init(fileName);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param fastDFSFile       fastdfs 上传文件对象
     * @throws IOException      异常处理
     * @return                  上传成功的文件信息数组
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        // 1、加载tracker信息
        // 2、创建一个Tracker访问的客户端对象 TrackerClient
        // 3、通过TrackerClient访问TrackerServer服务，获取链接信息
        TrackerServer trackerServer = getTrackerServer();

        // 4、通过返回的Tracker 创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = getStorageClient(trackerServer);


        // 5、通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的文件信息

        // 模拟携带别的元信息
        NameValuePair[] metaList = new NameValuePair[2];
        metaList[0] = new NameValuePair("shootOn","Iphone 13 Pro Max");
        metaList[1] = new NameValuePair("location","广东省-深圳市-南山区");

        try {
            // String[0]:文件上传所存储的Storage的组的名字       group1
            // String[1]:文件在Storage上的文件名字       M00/01/22/avatar.jpg
            String[] uploadFileInfo = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), metaList);
            return uploadFileInfo;
        } catch (MyException e) {
            e.printStackTrace();
        }
        return new String[0];
    }


    // 获取文件信息

    /**
     * 获取文件信息
     * @param groupName         文件组名
     * @param remoteFileName    文件的存储路径名字       M00/00/00/avatar.jpg
     * @return                  文件信息
     * @throws Exception        操作异常
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        // 1、创建一个TrackerClient对象，通过trackerClient对象访问TrackerServer
        // 2、通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = getTrackerServer();

        // 3、通过TrackerServer获取Storage信息，创建StorageClient对象存储storage信息
        StorageClient storageClient = getStorageClient(trackerServer);

        // 4、获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     * @param groupName         文件组名
     * @param remoteFileName    文件的存储路径名字       M00/00/00/avatar.jpg
     * @return                  文件输入流
     * @throws Exception        操作异常
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception {
        // 1、创建一个TrackerClient对象，通过trackerClient对象访问TrackerServer
        // 2、通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = getTrackerServer();

        // 3、通过TrackerServer获取Storage信息，创建StorageClient对象存储storage信息
        StorageClient storageClient = getStorageClient(trackerServer);

        // 4、文件下载   将字节数组转换为输入流
        return new ByteArrayInputStream(storageClient.download_file(groupName,remoteFileName));
    }

    /**
     * 文件删除
     * @param groupName         文件组名
     * @param remoteFileName    文件的存储路径名字       M00/00/00/avatar.jpg
     * @return                  删除结果
     * @throws Exception        操作异常
     */
    public static Boolean deleteFile(String groupName, String remoteFileName) throws Exception {
        // 1、创建一个TrackerClient对象，通过trackerClient对象访问TrackerServer
        // 2、通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = getTrackerServer();

        // 3、通过TrackerServer获取Storage信息，创建StorageClient对象存储storage信息
        StorageClient storageClient = getStorageClient(trackerServer);

        // 4、删除文件
        int i = storageClient.delete_file(groupName, remoteFileName);
        return i > 0;
    }

    /**
     * 获取Storage信息
     * @return          storage信息
     * @throws Exception    异常处理
     */
    public static StorageServer getStorageInfo() throws Exception {
        // 1、创建一个TrackerClient对象，通过trackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();

        // 2、通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = trackerClient.getConnection();

        // 3、获取Storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }


    /***
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception{
        // 1、创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 2、通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 3、获取服务信息
        return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
    }

    /**
     * 获取Tracker信息
     * @return              tracker链接地址
     * @throws Exception    异常处理
     */
    public static String getTrackerInfo() throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        // tracker 的IP，http端口
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int trackerHttpPort = ClientGlobal.getG_tracker_http_port();
        return "http://" + ip + ":" + trackerHttpPort;
    }

    /**
     * 获取TrackerServer
     * @return      trackerServer
     * @throws Exception      异常处理
     */
    public static TrackerServer getTrackerServer() throws Exception {
        // 1、创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 2、通过TrackerClient获取TrackerServer对象
        return trackerClient.getConnection();
    }

    /**
     * 获取StorageClient
     * @param trackerServer     trackerServer
     * @return                  StorageClient
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer) {
        return new StorageClient(trackerServer,null);
    }
}
