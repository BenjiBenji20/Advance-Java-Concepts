package com.azathoth.SimpleCRUD.controller;

import com.azathoth.SimpleCRUD.model.UpdateUser;
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
    private final UpdateUser updateUser;

    public UserController(UserService userService, UpdateUser updateUser) {
        this.userService = userService;
        this.updateUser = updateUser;
    }

    /**
     * User registration
     * The user's username should be unique across
     * db contents and inputs cannot be empty,
     * otherwise this controller
     * will send a conflict message.
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserModel newUser) {
        String username = newUser.getUsername().trim();
        String completeName = newUser.getCompleteName().trim();
        String password = newUser.getPassword().trim();

        try {
            // validate for empty fields
            if(username.isEmpty() || completeName.isEmpty() || password.isEmpty()) {
                return new ResponseEntity<>("Input fields cannot be empty", HttpStatus.BAD_REQUEST);
            }

            Optional<UserModel> registeredUser = userService.registerUser(newUser);

            if(registeredUser.isEmpty()) {
                return new ResponseEntity<>("User already existed", HttpStatus.CONFLICT);
            }

            // save new user
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * User login using username and password as credentials passed to req body.
     * First, this controller will check for missing input fields.
     * Second, the  req body will pass to the service layer to check
     * if the user is authenticated.
     * Lastly, if the user is authenticated, then the user data will be pass as JSON
     */
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

    /**
     * Update
     * This update controller receives 5 parameters: username confirmation,
     * password confirmation, a new or default complete name, username and password
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        try {
            // check for user authentication
            Optional<UserModel> authenticatedUser = userService.userAuthentication(
                    updateUser.getConfirmingUsername(),
                    updateUser.getConfirmingPassword()
            );

            // validate for empty response
            if(authenticatedUser.isEmpty()) {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.CONFLICT);
            }

            // pass request to the service layer
            Optional<UserModel> optionalUser = userService.updateUser(
                    updateUser.getConfirmingUsername(), updateUser.getConfirmingPassword(),
                    updateUser.getCompleteName(), updateUser.getUsername(),
                    updateUser.getPassword()
            );

            return optionalUser
                    .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
