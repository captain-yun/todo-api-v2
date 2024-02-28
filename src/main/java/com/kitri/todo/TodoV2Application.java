package com.kitri.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TodoV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TodoV2Application.class, args);
    }

}
