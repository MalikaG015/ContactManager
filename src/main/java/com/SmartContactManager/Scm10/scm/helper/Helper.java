package com.SmartContactManager.Scm10.scm.helper;

import java.lang.ref.Cleaner.Cleanable;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){
       // AuthenticationPrincipal principal=(AuthenticationPrincipal)authentication.getPrincipal();
        if(authentication instanceof OAuth2AuthenticationToken){
            //google or github
            var oAuthenticationToken=(OAuth2AuthenticationToken)authentication;
            String clientId=oAuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User= (OAuth2User)authentication.getPrincipal();
            String username="";
            if(clientId.equalsIgnoreCase("google")){
                username= oauth2User.getAttribute("email").toString();

            }
            else if(clientId.equalsIgnoreCase("github")){
                username=oauth2User.getAttribute("email")!=null ? oauth2User.getAttribute("email").toString():oauth2User.getAttribute("login").toString()+"@gmail.com";

            }
            return username;
        }
        else{
             return authentication.getName();
        }
    }
}
