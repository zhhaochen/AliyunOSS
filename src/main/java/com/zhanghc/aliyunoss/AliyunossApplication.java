package com.zhanghc.aliyunoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class AliyunossApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunossApplication.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize(DataSize.ofMegabytes(10L)); //KB,MB,具体看DataSize的方法
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(10L));
        return factory.createMultipartConfig();
    }
}
