package com.SmartContactManager.Scm10.scm.repositories;

import java.util.Optional;

import com.SmartContactManager.Scm10.scm.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,String> {
    //custom query methods
    //custom finder methods
    //extra db related methods

    Optional<User> findByEmail(String email);
    
}
