package com.example.useroperationsapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableScheduling
@EnableWebSecurity
@SpringBootApplication
public class UserOperationsAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserOperationsAppApplication.class, args);
    }
}