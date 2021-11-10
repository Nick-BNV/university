package ru.university.app.university.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {



    @GetMapping("/")
    @PreAuthorize("hasAuthority('developers:read')")
    public String start (Model model, Principal principal){
        model.addAttribute("name", principal.getName());
        return "/index";
    }
}
