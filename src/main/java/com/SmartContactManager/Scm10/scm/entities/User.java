package com.SmartContactManager.Scm10.scm.entities;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//entity means this table will be created in DB
@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    private String userId;
    @Column(name="user_name",nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;
    

    //SELF, GOOGLE,GIT
    @Enumerated(value=EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerId;


    //mapping
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getAbout() {
        return about;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public String getProviderId() {
        return providerId;
    }
    public Providers getProvider() {
        return provider;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    public boolean isEmailVerified() {
        return emailVerified;
    }
    public boolean isPhoneVerified() {
        return phoneVerified;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }
    public void setProvider(Providers provider) {
        this.provider = provider;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roleList=new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //list of roles[USER,ADMIN] converted to collection of simple granted authority
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    
}
