package com.ego.service;

import com.ego.result.FileResult;

import java.io.InputStream;

/**
 * Created by jick on 2019/3/26.
 */
public interface FileUploadServiceI {
    public FileResult   fileUpload(String fileName, InputStream inputStream);
    boolean    fileDelete(String pathName);
}
