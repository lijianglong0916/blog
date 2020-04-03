package com.blog.config;/*
 *@author:
 *@time
 */

import com.blog.utils.QCloudCosUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "spring.qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }


}
