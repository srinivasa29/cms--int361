package com.smartcontact.controller;

import com.smartcontact.model.User;
import com.smartcontact.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Always show landing page on root
    @GetMapping("/")
    public String landing() {
        return "landing"; // landing.html
    }

    // 📝 Register Page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 🔒 Handle Registration
    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if (userService.exists(user.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return "redirect:/register";
        }

        userService.register(user);
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }

    // 🔑 Login Page
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null)
            model.addAttribute("error", "Invalid username or password!");
        if (logout != null)
            model.addAttribute("info", "You have been logged out successfully!");
        return "login";
    }

    // 🚪 Logout Success
    @GetMapping("/logout-success")
    public String logoutSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("info", "You have successfully logged out!");
        return "redirect:/login";
    }
}
