package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.Specialty;
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
    return "iplan/all";}

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String add ( @RequestParam Integer lectures,
                        @RequestParam Integer practices,
                        @RequestParam Integer labs,
                        @RequestParam Integer consultations,
                        @RequestParam Integer controlWork,
                        @RequestParam Integer courseWork,
                        @RequestParam Integer exam,
                        @RequestParam Integer zachet,
                        Principal principal){
        String email = principal.getName();
        if(educationalWorkService.findByUser(email)==null){
            EducationalWork educationalWork = new  EducationalWork( lectures, practices, labs,  consultations, controlWork, courseWork,  exam,  zachet);
            educationalWorkService.save(educationalWork);}
        else {}

        return "iplan/all";}
}
