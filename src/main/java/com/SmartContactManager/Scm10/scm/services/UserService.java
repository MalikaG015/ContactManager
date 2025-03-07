package com.SmartContactManager.Scm10.scm.services;

import java.util.List;
import java.util.Optional;

import com.SmartContactManager.Scm10.scm.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional <User> getUserById(String id);
    User updateUser(User user);
    String deleteUser(String id);
    boolean isUserExists(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);



    
}
