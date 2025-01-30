package com.azathoth.dependency_injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Main.class, args);

        Demo obj = ac.getBean(Demo.class);

        obj.message();
    }
}
