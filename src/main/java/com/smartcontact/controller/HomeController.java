package com.smartcontact.controller;

import com.smartcontact.model.User;
import com.smartcontact.model.Contact;
import com.smartcontact.repository.UserRepository;
import com.smartcontact.service.ContactService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final UserRepository userRepo;
    private final ContactService contactService;

    public HomeController(UserRepository userRepo, ContactService contactService) {
        this.userRepo = userRepo;
        this.contactService = contactService;
    }

    // 🏡 Dashboard (After Login)
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails ud, Model model) {
        // 🛡️ If user is not logged in, redirect to login page
        if (ud == null) {
            return "redirect:/login";
        }

        User user = userRepo.findByUsername(ud.getUsername()).orElseThrow();
        List<Contact> contacts = contactService.getContacts(user);

        // ✅ Sort contacts by newest first
        contacts.sort(Comparator.comparing(Contact::getId).reversed());

        // ✅ Upcoming birthdays
        LocalDate today = LocalDate.now();
        List<Contact> upcomingBirthdays = contacts.stream()
                .filter(c -> {
                    if (c.getDob() == null) return false;
                    LocalDate nextBirthday = c.getDob().withYear(today.getYear());
                    if (nextBirthday.isBefore(today)) nextBirthday = nextBirthday.plusYears(1);
                    long days = ChronoUnit.DAYS.between(today, nextBirthday);
                    return days >= 0 && days <= 7;
                })
                .collect(Collectors.toList());

        // ✅ Category count (for charts)
        Map<String, Long> categoryCount = contacts.stream()
                .collect(Collectors.groupingBy(
                        c -> Optional.ofNullable(c.getCategory()).orElse("Others"),
                        Collectors.counting()
                ));

        model.addAttribute("contacts", contacts);
        model.addAttribute("upcomingBirthdays", upcomingBirthdays);
        model.addAttribute("categoryCount", categoryCount);

        return "dashboard"; // your dashboard.html
    }

}
