package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.service.DisciplineServiceImpl;
import ru.university.app.university.service.ListOfDisciplinesServiceImpl;
import ru.university.app.university.service.UserUniversityServiceImpl;

import java.util.ArrayList;

@Controller
@RequestMapping("/ListOfDisciplines")
public class ListOfDisciplinesController {
    @Autowired
    ListOfDisciplinesServiceImpl listOfDisciplinesServiceImpl;

    @Autowired
    UserUniversityServiceImpl userUniversityService;

    @Autowired
    DisciplineServiceImpl disciplineService;


    @GetMapping(path = "all")
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public String all(Model model){
        Iterable<ListOfDisciplines> iterable = listOfDisciplinesServiceImpl.findAll();
        model.addAttribute("list", iterable);
        return "ListOfDisciplines/all";
    }

    @GetMapping(path = "add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String add(){
        return "ListOfDisciplines/add";
    }


    @PostMapping(path="/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String add (
            @RequestParam UserUniversity users,
            @RequestParam Discipline disciplines) {

        ListOfDisciplines l =new ListOfDisciplines(users, disciplines);
        listOfDisciplinesServiceImpl.save(l);
        return "группа добавлена";
    }

    @ModelAttribute("users")
    public Iterable<UserUniversity>  getUser (){
        return userUniversityService.getAllUsers();
    }

    @ModelAttribute("disciplines")
    public Iterable <Discipline>  getDiscipline (){
        return disciplineService.getAll();
    }

}
