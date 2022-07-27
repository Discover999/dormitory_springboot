package com.dormitory_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dormitory_springboot.mapper")
public class DormitorySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitorySpringbootApplication.class, args);
    }

}
