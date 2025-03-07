package com.SmartContactManager.Scm10.scm.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {   
    @NotBlank(message = "username is required") 
    @Size(min=3, message="Minimum 3 characters are required")
    private String name; 
    @Email(message="invalid email address")
    @NotBlank(message = "email is required")
    private String email;
    @Size(min=6,message = "Minimum 6 characters are required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min=8,max=12,message="Invalid phone number")
    private String phoneNumber;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getAbout() {
        return about;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}