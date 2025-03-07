package com.SmartContactManager.Scm10.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import com.SmartContactManager.Scm10.scm.entities.User;
import com.SmartContactManager.Scm10.scm.helper.AppConstants;
import com.SmartContactManager.Scm10.scm.helper.ResourceNotFoundException;
import com.SmartContactManager.Scm10.scm.repositories.userRepo;
import com.SmartContactManager.Scm10.scm.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //user is  have to generate
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Update fields
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAbout(user.getAbout());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProviderId(user.getProviderId());
        existingUser.setProvider(user.getProvider());

        // Save the updated user
        return userRepo.save(existingUser);
    }

    @Override
    public String deleteUser(String id) {
        //fetch user
        User User2= userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
         userRepo.delete(User2);
         return "user deleted successfully";

        
    }

    @Override
    public boolean isUserExists(String userId) {
        User User2= userRepo.findById(userId).orElse(null);
        return User2!=null ? true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User User2=userRepo.findByEmail(email).orElse(null);
        return User2!=null ?true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
    
}
