package com.ego.service.impl;

import com.ego.result.FileResult;
import com.ego.service.FileUploadServiceI;
import com.ego.util.DateUtil;
import com.ego.util.FTPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Created by jick on 2019/3/26.
 */
@Service
public class FileUploadServiceImpl implements FileUploadServiceI {

    @Value("${ftp.host}")
     private  String   host;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.path}")
    private String path;


    /*
      文件上传
     */

    @Override
    public FileResult fileUpload(String fileName, InputStream inputStream) {
      //构建日期时间路径  /home/ftpuser/ego/2019/03/26
        String  dateStr = DateUtil.getDateStr(LocalDateTime.now(),DateUtil.pattern_date);
        String     basePath =   path + dateStr;

        //文件上传成功返回服务器文件名
        String  result   = FTPUtil.fileUpload(host,port,username,password,basePath,fileName,inputStream);

        //创建返回对象
        FileResult   fr  =  new FileResult();
        if(null==result){
            fr.setError("error");
            fr.setMessage("文件上传失败");
            return    fr;
        }

        //最终存储至数据库的文件地址为 ip/2019/03/26/resutlt
        fr.setSuccess("success");
        fr.setFileUrl("http://"+host+"/"+dateStr+"/"+result);
        return    fr;
    }

    @Override
    public boolean fileDelete(String pathName) {
        return  FTPUtil.fileDelete(host,port,username, password, pathName, path);
    }
}
