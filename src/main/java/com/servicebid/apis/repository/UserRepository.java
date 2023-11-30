package com.servicebid.apis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicebid.apis.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserID(long UserID);
    List<User> findByIsEnabled(boolean isEnabled);
    List<User> findByEmailAddress(String emailAddress);
    List<User> findByNameContaining(String namefragment);
}
