package com.azathoth.SimpleCRUD.middleware;

import com.azathoth.SimpleCRUD.model.UserModel;
import com.azathoth.SimpleCRUD.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuth {

  private final UserRepository userRepository;

    public UserAuth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // hashed password
    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    // compare hashed password and plain password
    public boolean compareInputPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
