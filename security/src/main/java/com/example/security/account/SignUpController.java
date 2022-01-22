package com.example.security.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public String signupForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";

    }

    @PostMapping
    public String processSignUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }

}
