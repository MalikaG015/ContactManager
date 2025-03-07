package com.SmartContactManager.Scm10.scm.forms;

import com.SmartContactManager.Scm10.scm.validators.ValidFile;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "Name is required") 
    private String name;
    @Email(message="invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone number is required") 
    @Size(min=8,max=12,message="Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = "Address is required") 
    private String address;
    private String description;
    private boolean favourite;
    private String websiteLink;
    private String linkedInLink;

    @ValidFile(message="Invalid file")
    private MultipartFile contactImage;

    private String picture;

}
