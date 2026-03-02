package com.example.springboot;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    // 🔹 Show Login Page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // 🔹 Handle Login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}