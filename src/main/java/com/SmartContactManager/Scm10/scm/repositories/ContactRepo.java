package com.SmartContactManager.Scm10.scm.repositories;

import com.SmartContactManager.Scm10.scm.entities.User;

import java.util.List;

import com.SmartContactManager.Scm10.scm.entities.Contact;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

public interface ContactRepo extends JpaRepository<Contact,String> {

    //find contacts by user
    Page<Contact> findByUser(User user, Pageable pageable);

    @Query("Select c from Contact c where c.user.id=:userId")
    List<Contact> findByUserId(@Param("userId") String userId);
    Page<Contact> findByUserAndNameContaining(User user,String namekeyword,Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword,Pageable pageable);
    
}
