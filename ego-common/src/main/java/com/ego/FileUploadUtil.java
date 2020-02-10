package com.ego;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by jick on 2019/3/27.
 */

//文件上传
public class FileUploadUtil {

    public static void main(String[] args) {
        FTPClient   ftpClient  =  new FTPClient();

        ftpClient.setControlEncoding("UTF-8");

        InputStream  local = null;


        try {
            ftpClient.connect("192.168.100.25",21);

            ftpClient.login("ftpuser","egodylan");

            int  replay  =  ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(replay)){
                ftpClient.disconnect();
                return ;
            }



            //本地输入流
            File file =  new File("d:\\img.jpg");
            local  = new FileInputStream(file);

            String    path =   "/home/ftpuser/ego";

            String  basePath = "/";
            for(String p : path.split("/")){
                basePath +=(p+"/");
                boolean  haspath =ftpClient.changeWorkingDirectory(basePath);

                if(!haspath){
                    ftpClient.makeDirectory(basePath);
                }


            }

            ftpClient.changeWorkingDirectory(path);

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //获取文件后缀
            String    suffix =   file.getName().substring(file.getName().lastIndexOf("."));

            ftpClient.storeFile(UUID.randomUUID().toString()+suffix,local);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(local !=null){
                try {
                    local.close();
                    ftpClient.logout();

                    if(ftpClient.isConnected()){
                        ftpClient.disconnect();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}