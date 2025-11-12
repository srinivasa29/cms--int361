package com.smartcontact.controller;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;
import com.smartcontact.repository.UserRepository;
import com.smartcontact.service.ContactService;
import com.smartcontact.service.MailService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserRepository userRepo;
    private final MailService mailService;

    public ContactController(ContactService contactService, UserRepository userRepo, MailService mailService) {
        this.contactService = contactService;
        this.userRepo = userRepo;
        this.mailService = mailService;
    }

    // 📋 List all contacts
    @GetMapping
    public String list(Model model, Principal principal,
                       @RequestParam(value = "query", required = false) String query,
                       @ModelAttribute("success") String successMessage,
                       @ModelAttribute("info") String infoMessage) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        List<Contact> contacts = (query == null || query.isBlank()) ?
                contactService.getContacts(user) :
                contactService.searchContacts(query, user);

        model.addAttribute("contacts", contacts);
        model.addAttribute("searchQuery", query == null ? "" : query);

        // pass SweetAlert messages to frontend
        if (successMessage != null && !successMessage.isBlank()) {
            model.addAttribute("success", successMessage);
        }
        if (infoMessage != null && !infoMessage.isBlank()) {
            model.addAttribute("info", infoMessage);
        }

        return "contacts";
    }

    // 🔍 Search endpoint
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, Principal principal) {
        return list(model, principal, query, null, null);
    }

    // ➕ Add form
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    // 💾 Save new or updated contact
    @PostMapping("/save")
    public String save(@ModelAttribute Contact contact, Principal principal, RedirectAttributes redirectAttributes) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        contact.setUser(user);
        contactService.save(contact);

        // 📧 Send notification
        mailService.sendContactSavedMail(user, contact);

        redirectAttributes.addFlashAttribute("success", "Contact saved successfully!");
        return "redirect:/contacts";
    }

    // ✏️ Edit contact
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, Principal principal) {
        Contact contact = contactService.findById(id).orElseThrow();
        if (!contact.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/contacts";
        }
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    // ❌ Delete contact
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        contactService.delete(id);
        redirectAttributes.addFlashAttribute("info", "Contact deleted successfully!");
        return "redirect:/contacts";
    }

    // 📤 Export contacts as CSV
    @GetMapping("/export")
    public void exportCsv(HttpServletResponse response, Principal principal) throws Exception {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"contacts.csv\"");

        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        List<Contact> contacts = contactService.getContacts(user);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("Name,Email,Phone,Category,DOB");
            for (Contact c : contacts) {
                writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                        escape(c.getName()),
                        escape(c.getEmail()),
                        escape(c.getPhone()),
                        escape(c.getCategory()),
                        c.getDob() == null ? "" : c.getDob().toString());
            }
            writer.flush();
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\"\"");
    }
}
