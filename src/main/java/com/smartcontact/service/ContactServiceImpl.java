package com.smartcontact.service;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;
import com.smartcontact.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getContacts(User user) {
        return contactRepository.findByUser(user);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> searchContacts(String query, User user) {
        if (query == null || query.isBlank()) {
            return contactRepository.findByUser(user);
        }
        String q = "%" + query.toLowerCase().trim() + "%";
        return contactRepository.findByUserAndSearch(user.getId(), q);
    }
}
