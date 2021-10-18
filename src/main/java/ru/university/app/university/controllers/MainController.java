package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.StudyGgroup;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.StudyGroupRepo;
import ru.university.app.university.repo.UsersRepo;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class MainController {
    @Autowired
    private UsersRepo usersRepo;
    @GetMapping("/success")
    public @ResponseBody String success (Principal principal){
        return "privet "+principal.getName() +"!";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserUniversity> getAllUsers() {
        return usersRepo.findAll();
    }


    @GetMapping(path = "/add")
    public String addForm (){
        return "/admin/add";
    }

    @PostMapping(path="/add")
    @ResponseBody
    // не рабоает
    public  String addNewUser (
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String middle_name,
            @RequestParam String surname) {

        UserUniversity user = new UserUniversity(email, name, middle_name, surname);
        usersRepo.save(user);
        return "пользователь добавлен";
    }


}
