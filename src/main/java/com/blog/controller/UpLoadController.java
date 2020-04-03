package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.service.FileUploadService;
import com.blog.service.HeadPictureService;
import com.blog.utils.FileUpLoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class UpLoadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private HeadPictureService headPictureService;

    /**
     * 文章中图片文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("upLoadPicture")
    @ResponseBody
    public FileUpLoadUtil upLoadPicture(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file) {
        if (file == null) {
            return new FileUpLoadUtil(0, "上传失败", null);
        }
        String upload = null;
        try {
            upload = fileUploadService.upload(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new FileUpLoadUtil(0, "上传失败", null);
        }
        if (upload != null) {
            return new FileUpLoadUtil(1, "上传成功", upload);
        } else {
            return new FileUpLoadUtil(0, "上传失败", null);
        }
    }

    /**
     * 头像上传
     *
     * @param file
     * @return
     */
    @PostMapping("imageUpload")
    @ResponseBody
    public FileUpLoadUtil upLoadHeadPicture(@RequestParam("fileName") MultipartFile file, HttpSession session) {
        if (file == null) {
            return new FileUpLoadUtil(0, "上传失败！", null);
        }
        //上传图片到云服务器，并返回url
        String upload = null;
        try {
            upload = fileUploadService.upload(file);
            if (upload != null) {
                //根据当前登录用户账号更新头像信息
                try {
                    headPictureService.updateHP(upload, (Long) session.getAttribute("userAccount"));
                    return new FileUpLoadUtil(1, "上传成功！", null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new FileUpLoadUtil(0, "上传失败！", null);
                }

            } else {
                return new FileUpLoadUtil(0, "上传失败！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FileUpLoadUtil(0, "上传失败！", null);
        }

    }


}
