package com.blog.utils;/*
 *@author:
 *@time
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpLoadUtil {
    private int success;
    private String message;
    private String url;
}
