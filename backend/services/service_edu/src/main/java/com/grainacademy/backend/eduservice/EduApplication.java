package com.grainacademy.backend.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.grainacademy"})
public class EduApplication {

    //http://localhost:8001/swagger-ui/index.html
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}