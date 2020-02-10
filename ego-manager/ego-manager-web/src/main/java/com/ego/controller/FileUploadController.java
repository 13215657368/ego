package com.ego.controller;

import com.ego.result.FileResult;
import com.ego.service.FileUploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jick on 2019/3/26.
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {
    @Autowired
    private FileUploadServiceI   fileUploadServiceI;

    @RequestMapping("/save")
    @ResponseBody
    public FileResult    fileResult(MultipartFile file){

        try {

           return   fileUploadServiceI.fileUpload(file.getOriginalFilename(),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
