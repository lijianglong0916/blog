package com.blog.service;/*
 *@author:
 *@time
 */

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public interface FileUploadService {
    /**
     * 处理浏览器文件上传请求
     *
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);

    /**
     * 处理普通文件上传
     *
     * @param file
     * @return
     */
    String upload(File file);
}
