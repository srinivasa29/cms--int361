package com.smartcontact.service;

import com.smartcontact.model.User;
import com.smartcontact.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ encode password
        userRepo.save(user);
    }

    public boolean exists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }
}
