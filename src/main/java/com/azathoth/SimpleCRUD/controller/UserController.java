package com.azathoth.SimpleCRUD.controller;

import com.azathoth.SimpleCRUD.model.UserConfirmationSecurity;
import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*") // allow all port origins (for front end)
@Controller
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserConfirmationSecurity userConfirmationSecurity;

    // making message as object to pass as api response
    private final Map<String, String> response = new HashMap<>();

    public UserController(UserService userService, UserConfirmationSecurity userConfirmationSecurity) {
        this.userService = userService;
        this.userConfirmationSecurity = userConfirmationSecurity;

        response.put("message", ""); // key value pair for non error response
        response.put("error", "");
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
                response.replace("error", "Input fields cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Optional<UserModel> registeredUser = userService.registerUser(newUser);

            if(registeredUser.isEmpty()) {
                response.replace("error", "User already existed");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // save new user
            response.replace("message", "User registered successfully!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    @PostMapping("/auth")
    public ResponseEntity<?> userAuthentication(@RequestBody UserModel user) { // only pass username and password
        String username = user.getUsername().trim();
        String password = user.getPassword().trim();

        try {
            // validate user inputs
            if(username.isEmpty() || password.isEmpty()) {
                response.replace("error", "Input fields cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // check if user is authenticated
            Optional<UserModel> authenticatedUser = userService.userAuthentication(user.getUsername(), user.getPassword());

            // if authenticated then pass http response
            if(authenticatedUser.isPresent()) {
                return new ResponseEntity<>(authenticatedUser.get(), HttpStatus.OK);
            }
            else {
                response.replace("error", "Invalid username or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<?> updateUser(@RequestBody UserConfirmationSecurity updateUser) {
        try {
            // check for user authentication
            Optional<UserModel> authenticatedUser = userService.userAuthentication(
                    updateUser.getConfirmingUsername(),
                    updateUser.getConfirmingPassword()
            );

            // validate for empty response
            if(authenticatedUser.isEmpty()) {
                response.replace("error", "Invalid username or password");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
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

    /**
     * Delete
     * This update controller receives 2 parameters: username confirmation and
     * password confirmation. These parameters will use for user authentication
     * inside service layer
     *
     */
    @DeleteMapping("/delete-my-account")
    public ResponseEntity<?> deleteUser(@RequestBody UserConfirmationSecurity deleteUser) {
        try {
            // check for user authentication
            Optional<UserModel> authenticatedUser = userService.userAuthentication(
                    deleteUser.getConfirmingUsername(),
                    deleteUser.getConfirmingPassword()
            );

            // validate for empty response
            if(authenticatedUser.isEmpty()) {
                response.replace("error", "Invalid username or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            // pass request to the service layer
            boolean isUserDeleted = userService.deleteUser(
                    deleteUser.getConfirmingUsername(),
                    deleteUser.getConfirmingPassword()
            );

            // send response based on whether the user is deleted in service layer
            return isUserDeleted ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get all users
    @GetMapping("/user-list")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserModel> userList = userService.getAllUsers();

            if(userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error found: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // search users
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchUser(@PathVariable String keyword) {
        List<UserModel> userList = userService.searchUser(keyword);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
