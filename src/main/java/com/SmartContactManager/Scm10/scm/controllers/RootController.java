package com.SmartContactManager.Scm10.scm.controllers;

import com.SmartContactManager.Scm10.scm.entities.User;
import com.SmartContactManager.Scm10.scm.helper.Helper;
import com.SmartContactManager.Scm10.scm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
//now methods inside this will execute before request
public class RootController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
        if(authentication==null){
            return;
        }
        String username=Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);
        model.addAttribute("loggedInUser",user);
    }
    
}
