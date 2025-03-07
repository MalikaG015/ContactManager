package com.SmartContactManager.Scm10.scm.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SmartContactManager.Scm10.scm.services.impl.CustomSecurityUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //create user and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user1=User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN").build();
    //     return new InMemoryUserDetailsManager(user1); 
    // }
    @Autowired
    private CustomSecurityUserDetailsService userDetailsService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        })
        .formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            //formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
        });
        //oauth configurations
        //httpSecurity.oauth2Login(Customizer.withDefaults());
        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

    return httpSecurity.build();
}



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
}
