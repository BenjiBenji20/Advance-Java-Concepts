package com.azathoth.SimpleCRUD.service;

import com.azathoth.SimpleCRUD.middleware.UserAuth;
import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
