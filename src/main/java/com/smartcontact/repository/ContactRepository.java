package com.smartcontact.repository;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Return contacts belonging to a user
    List<Contact> findByUser(User user);

    // Search by name/email/phone (case-insensitive)
    @Query("select c from Contact c where c.user.id = :userId and (" +
            "lower(c.name) like :q or lower(c.email) like :q or lower(c.phone) like :q" +
            ")")
    List<Contact> findByUserAndSearch(@Param("userId") Long userId, @Param("q") String q);

}
