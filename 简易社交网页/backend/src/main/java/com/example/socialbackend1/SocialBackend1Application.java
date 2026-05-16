package com.example.socialbackend1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.example.socialbackend1.mapper")
public class SocialBackend1Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SocialBackend1Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir") + "/upload/avatar/";
        registry.addResourceHandler("/upload/avatar/**")
                .addResourceLocations("file:" + path);
    }
}