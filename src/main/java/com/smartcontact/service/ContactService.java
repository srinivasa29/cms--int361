package com.smartcontact.service;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getContacts(User user);
    Contact save(Contact contact);
    Optional<Contact> findById(Long id);
    void delete(Long id);

    // for search
    List<Contact> searchContacts(String query, User user);
}
