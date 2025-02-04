package com.azathoth.SimpleCRUD.controller;

import com.azathoth.SimpleCRUD.middleware.UserAuth;
import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserAuth userAuth;

    public UserController(UserService userService, UserAuth userAuth) {
        this.userService = userService;
        this.userAuth = userAuth;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> greeting(@RequestBody UserModel newUser) {
        String username = newUser.getUsername().trim();
        String completeName = newUser.getCompleteName().trim();
        String password = newUser.getPassword().trim();

        try {
            // check if the user is already in db
            if(username.isEmpty() || completeName.isEmpty() || password.isEmpty()) {
                return new ResponseEntity<>("Input fields cannot be empty", HttpStatus.BAD_REQUEST);
            }
            else if(userAuth.isAuthenticated(newUser.getUsername())){
                return new ResponseEntity<>("Username already been used", HttpStatus.CONFLICT);
            }
            else {
                // save new user
                return new ResponseEntity<>(userService.registerUser(newUser), HttpStatus.CREATED);
            }
        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
