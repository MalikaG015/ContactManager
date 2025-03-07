package com.SmartContactManager.Scm10.scm.controllers;

import com.SmartContactManager.Scm10.scm.entities.Contact;
import com.SmartContactManager.Scm10.scm.services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId){
        return contactService.getById(contactId);

    }
    
}
