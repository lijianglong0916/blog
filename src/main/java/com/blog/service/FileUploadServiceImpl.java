package com.blog.service;/*
 *@author:
 *@time
 */

import com.blog.utils.QCloudCosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private QCloudCosUtils qCloudCosUtils;

    @Override
    public String upload(MultipartFile multipartFile) {
        return qCloudCosUtils.upload(multipartFile);
    }

    @Override
    public String upload(File file) {
        return qCloudCosUtils.upload(file);
    }
}
