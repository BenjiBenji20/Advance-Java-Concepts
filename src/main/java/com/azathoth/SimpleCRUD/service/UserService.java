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

    /**
     * Controller layer will pass new user
     * and using this service end point, the user's password
     * will be hashed for security and will be save to the db
     */
    public UserModel registerUser(UserModel user) {
        String hashedPassword = userAuth.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    /**
     *This service end point is for user login/authentication.
     * It first checks in the db if the passed username is
     * available, if not then it will return an empty object.
     * If the username is available in the db, the new user object
     * will get the data.
     * Lastly, the passed plainPassword will compare to the
     * hashed password using mindrot dependency if matched then this service
     * will return a user data, otherwise an empty object.
     */
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
