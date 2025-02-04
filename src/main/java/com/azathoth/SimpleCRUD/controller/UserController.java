package com.azathoth.SimpleCRUD.controller;

import com.azathoth.SimpleCRUD.middleware.UserAuth;
import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> registerUser(@RequestBody UserModel newUser) {
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> userAuthentication(@RequestBody UserModel user) { // only pass username and password
        String username = user.getUsername().trim();
        String password = user.getPassword().trim();

        try {
            // validate user inputs
            if(username.isEmpty() || password.isEmpty()) {
                return new ResponseEntity<>("Input fields cannot be empty", HttpStatus.BAD_REQUEST);
            }

            // check if user is authenticated
            Optional<UserModel> authenticatedUser = userService.userAuthentication(user.getUsername(), user.getPassword());

            // if authenticated then pass http response
            if(authenticatedUser.isPresent()) {
                return new ResponseEntity<>(authenticatedUser.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }

        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
