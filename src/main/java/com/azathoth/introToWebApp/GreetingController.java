package com.azathoth.introToWebApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public String greet() {
        return "Hello World by Azathoth using Spring Boot!";
    }

    @RequestMapping("/home")
    public String home() {
        return "You are in a home page...";
    }
}
