package com.pinyougou.shop.controller;

import com.pinyougou.util.FastDFSClient;
import entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制类
 * @author 杰威力
 * 2019/7/4 21:23
 */
@RestController
public class UploadController {

    @RequestMapping("/upload")
    public Result upload(MultipartFile file){
        //获取文件扩展名
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf(".") + 1);

        try {
            //获取fastClient
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
            //上传文件 获取到 文件的访问地址 fileIp
            String fileIp = client.uploadFile(file.getBytes(), extName);
            return new Result(true, fileIp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return new Result(false, "上传失败,请查看网络并重试");
    }
}
