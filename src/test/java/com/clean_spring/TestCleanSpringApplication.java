package com.clean_spring;

import org.springframework.boot.SpringApplication;

public class TestCleanSpringApplication {

    public static void main(String[] args) {
        SpringApplication.from(CleanSpringApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
