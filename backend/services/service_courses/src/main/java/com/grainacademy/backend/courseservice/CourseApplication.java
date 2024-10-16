package com.grainacademy.backend.courseservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(
        title="Grain Academy",
        version = "1.0",
        description = "This is an API management platform for the Grain Academy",
        contact = @Contact(name = "Zejia Cai", url = "https://www.linkedin.com/in/zejia-cai-053259224/", email = "ZejiaCai01@gmail.com"),
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
))
@ComponentScan(basePackages = {"com.grainacademy.backend"})
public class CourseApplication {
    //http://localhost:8003/doc.html#/home
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
