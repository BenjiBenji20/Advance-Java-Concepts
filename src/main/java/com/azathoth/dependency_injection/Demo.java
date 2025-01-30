package com.azathoth.dependency_injection;

import org.springframework.stereotype.Component;

@Component
public class Demo {

    // trying di using simple bean
    public void message() {
        System.out.println("This is a message from Spring Boot");
    }
}
