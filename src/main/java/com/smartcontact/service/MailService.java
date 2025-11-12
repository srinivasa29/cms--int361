package com.smartcontact.service;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;

public interface MailService {
    void sendContactSavedMail(User user, Contact contact);
}
