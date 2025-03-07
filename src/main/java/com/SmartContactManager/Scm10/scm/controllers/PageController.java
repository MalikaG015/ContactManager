package com.SmartContactManager.Scm10.scm.controllers;

import com.SmartContactManager.Scm10.scm.entities.User;
import com.SmartContactManager.Scm10.scm.forms.UserForm;
import com.SmartContactManager.Scm10.scm.helper.Message;
import com.SmartContactManager.Scm10.scm.helper.MessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.SmartContactManager.Scm10.scm.services.UserService;


@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("home page handler");
        model.addAttribute("name","malika");
        return "home";

    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("about page");
        return "about";

    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services page");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage(){
        System.out.println("contact page");
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage(){
        System.out.println("login page");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // we can add default data also
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "register";
    }


    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session){
        System.out.println("processing register");
        if(rBindingResult.hasErrors()){
            return "register";
        }
        System.out.println(userForm.getName());
        //we have to fetch the form data,validate it and save to DB, finally redirect to login page
        //fetch the data
        // User user=User.builder()
        // .name(userform.getName())
        // .email(userform.getEmail())
        // .password(userform.getPassword())
        // .profilePic("https://www.vectorstock.com/royalty-free-vector/default-profile-picture-avatar-user-icon-vector-46389216")
        // .about(userform.getAbout())
        // .phoneNumber(userform.getPhoneNumber())
        // .build();
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setProfilePic("https://www.vectorstock.com/royalty-free-vector/default-profile-picture-avatar-user-icon-vector-46389216");
        user.setPhoneNumber(userForm.getPhoneNumber());


        User savedUser=userService.saveUser(user);
        System.out.println("user saved");
        Message message=Message.builder().content("Registration successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";

    }
    
    @GetMapping("/")
    public String index(){
        return "redirect:/home";

    }
}
//src/main/java/com/SmartContactManager/Scm10/scm/controllers/PageController.java