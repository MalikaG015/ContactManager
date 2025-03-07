package com.SmartContactManager.Scm10.scm.controllers;

import java.security.Principal;

import com.SmartContactManager.Scm10.scm.entities.User;
import com.SmartContactManager.Scm10.scm.helper.Helper;
import com.SmartContactManager.Scm10.scm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //user dashboard page

    // @RequestMapping(value="/dashboard",method=RequestMethod.GET)
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile")
    public String userProfile(Model model,Authentication authentication){
        String username=Helper.getEmailOfLoggedInUser(authentication);
        User user=userService.getUserByEmail(username);
        System.out.println(user.getEmail());
        System.out.println(user.getName());

        model.addAttribute("loggedInUser",user);

        return "user/profile";
    }

    
}
