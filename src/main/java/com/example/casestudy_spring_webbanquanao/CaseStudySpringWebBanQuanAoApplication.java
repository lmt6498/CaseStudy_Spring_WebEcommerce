package com.example.casestudy_spring_webbanquanao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CaseStudySpringWebBanQuanAoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseStudySpringWebBanQuanAoApplication.class, args);
    }

}
