package com.blog.utils;/*
 *@author:
 *@time
 */

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 工具类
 */
public class BlogUtils {
    private static Logger log = Logger.getAnonymousLogger();

    /**
     * 获取当前登录的用户名（唯一）
     *
     * @param session
     * @return
     */
    public static String getCurrentUser(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        return userName;
    }

    /**
     * 判断 objects中是否有空对象，有返回true，没有返回false
     *
     * @param objects
     * @return
     */
    public static boolean isObjectNull(Object... objects) {
        boolean isNull = false;
        log.setLevel(Level.OFF);
        int i = 0;
        for (Object object : objects) {
            if (null == object) {
                log.info("下标第" + i + "个对象为空" + "null");
                isNull = true;
                return isNull;
            }
            i++;


        }
        return isNull;
    }
}
