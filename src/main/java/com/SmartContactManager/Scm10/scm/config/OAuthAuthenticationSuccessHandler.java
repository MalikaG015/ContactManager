package com.SmartContactManager.Scm10.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.SmartContactManager.Scm10.scm.entities.Providers;
import com.SmartContactManager.Scm10.scm.entities.User;
import com.SmartContactManager.Scm10.scm.helper.AppConstants;
import com.SmartContactManager.Scm10.scm.repositories.userRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private userRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                //DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();

                //determine if oauth2 is google or github login
                var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
                String authorizedClientRegistrationId =oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

                //System.out.println(authorizedClientRegistrationId);
                DefaultOAuth2User oauthuser=(DefaultOAuth2User)authentication.getPrincipal();
                oauthuser.getAttributes().forEach((key,value)->{

                });
                User user=new User();
                user.setUserId(UUID.randomUUID().toString());
                user.setRoleList(List.of(AppConstants.ROLE_USER));
                user.setEmailVerified(true);
                user.setEnabled(true);
                user.setPassword("dummy");

                if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                    user.setEmail(oauthuser.getAttribute("email").toString());
                    user.setProfilePic(oauthuser.getAttribute("picture").toString());
                    user.setName(oauthuser.getAttribute("name").toString());
                    user.setProviderId(oauthuser.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setAbout("This account is created using google");

                }
                else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                    String email=oauthuser.getAttribute("email")!=null ? oauthuser.getAttribute("email").toString():oauthuser.getAttribute("login").toString()+"@gmail.com";
                    String picture=oauthuser.getAttribute("avatar_url").toString();
                    String name=oauthuser.getAttribute("login").toString();
                    String providerId=oauthuser.getName();

                    user.setEmail(email);
                    user.setProfilePic(picture);
                    user.setName(name);
                    user.setProviderId(providerId);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("This account is created using github");

                }

                // //If user exists then don't save if not then save
                User user2=userRepo.findByEmail(user.getEmail()).orElse(null);
                if(user2==null){
                    userRepo.save(user);
                    System.out.println("user saved");
                }


                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        
    }

    
}
