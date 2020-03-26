package com.blog;/*
 *@author:
 *@time
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication() // 相当于 @ComponentScan，@EnableAutoConfiguration，@Configuration这三个注解
//@EnableCaching
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);

    }
}
