package com.azathoth.SimpleCRUD.repository;

import com.azathoth.SimpleCRUD.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    // JPQL
    @Query("SELECT u from UserModel u WHERE " +
            "LOWER(u.completeName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<UserModel> searchUser(String keyword);
}
