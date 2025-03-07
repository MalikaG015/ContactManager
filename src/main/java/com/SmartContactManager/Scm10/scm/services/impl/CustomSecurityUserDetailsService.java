package com.SmartContactManager.Scm10.scm.services.impl;

import com.SmartContactManager.Scm10.scm.repositories.userRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomSecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        // need to load our user
        return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
    
}
