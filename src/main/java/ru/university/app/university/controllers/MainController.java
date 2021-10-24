package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.service.UserUniversityService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class MainController {
    //@Autowired
    //private UsersRepo usersRepo;

    private final UserUniversityService userUniversityService;

    @Autowired
    public MainController(UserUniversityService userUniversityService){
        this.userUniversityService = userUniversityService;
    }

    @GetMapping("/success")
    @ResponseBody
    public String success (Principal principal){
        return "privet "+principal.getName() +"!";
    }


    @GetMapping(path="/all")
    @PreAuthorize("hasAuthority('developers:read')")
    @ResponseBody
    public Iterable<UserUniversity> getAllUsers() {
        return userUniversityService.getAllUsers();
    }


    @GetMapping(path = "/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String addForm (){
        return "/admin/add";
    }



    @PostMapping(path="/add")
    @PreAuthorize("hasAuthority('developers:write')")
    @ResponseBody
    public  String addNewUser (
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String middle_name,
            @RequestParam String surname) {

        UserUniversity user = new UserUniversity(email, name, middle_name, surname);
        userUniversityService.saveUser(user);
        return "пользователь добавлен";
    }


}
