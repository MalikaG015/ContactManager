package com.SmartContactManager.Scm10.scm.services;

import java.util.List;

import com.SmartContactManager.Scm10.scm.entities.User;

import org.springframework.data.domain.Page;


import com.SmartContactManager.Scm10.scm.entities.Contact;



public interface ContactService {

    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByEmail(String emailKeyword,int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size, int page, String sortBy, String order, User user);
    List<Contact> getByUserId(String userId);
    Page<Contact> getByUser(User user,int page, int size, String sortField, String sortDirection);
 
}
