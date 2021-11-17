package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.EducationalWorkRepo;
import ru.university.app.university.service.EducationalWorkService;
import ru.university.app.university.service.EducationalWorkServiceImpl;
import ru.university.app.university.service.UserUniversityServiceImpl;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/IndividualPlan")
public class IndividualPlanController {

    @Autowired
    EducationalWorkServiceImpl educationalWorkService;


    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String all(Principal principal, Model model){
        String email = principal.getName();
        model.addAttribute("iplan", educationalWorkService.findByUser(email));
    return "iplan/all";
}
}
