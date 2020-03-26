package com.blog.utils;/*
 *@author:
 *@time
 */

import org.springframework.web.servlet.ModelAndView;

/**
 * ModelAndView
 */
public class ViewResultUtils {
    public static ModelAndView addView(ModelAndView view, String status, Object data) {
        view.addObject("status", status);
        view.addObject("data", data);
        return view;
    }

}
