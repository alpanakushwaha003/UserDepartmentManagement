package com.javalearn.learnSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching

public class UserDepartment {

    public static void main(String[] args) {
        SpringApplication.run(UserDepartment.class, args);
    }
}
