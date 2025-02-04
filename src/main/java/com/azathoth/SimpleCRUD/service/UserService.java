package com.azathoth.SimpleCRUD.service;

import com.azathoth.SimpleCRUD.middleware.UserAuth;
import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserAuth userAuth;

    public UserService(UserRepository userRepository, UserAuth userAuth) {
        this.userRepository = userRepository;
        this.userAuth = userAuth;
    }

    public UserModel registerUser(UserModel user) {

        String hashedPassword = userAuth.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public Optional<UserModel> userAuthentication(String username, String plainPassword) {
        Optional<UserModel> loggingUser = userRepository.findByUsername(username);

        // if the input username didn't exist in db, then return empty;
        if(loggingUser.isEmpty()) {
            return Optional.empty();
        }

        // extract user data if found
        UserModel user = loggingUser.get();

        // check for password matching
        boolean isPasswordMatched = userAuth.compareInputPassword(plainPassword, user.getPassword());

        if(isPasswordMatched) {
            return Optional.of(user);
        }

        // else, return empty
        return  Optional.empty();
    }
}
