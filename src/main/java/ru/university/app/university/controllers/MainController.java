package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.service.DisciplineService;
import ru.university.app.university.service.UserUniversityServiceImpl;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    UserUniversityServiceImpl userUniversityService;


    @GetMapping("/")
    @PreAuthorize("hasAuthority('developers:read')")
    public String start (Model model, Principal principal){
        ArrayList<UserUniversity> list = userUniversityService.getByEmail(principal.getName());
        String s = list.get(0).getName()+" "+list.get(0).getSurname();
        model.addAttribute("name", s);
        return "/index";
    }


}
