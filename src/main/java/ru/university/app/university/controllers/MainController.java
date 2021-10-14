package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.university.app.university.models.StudyGgroup;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.StudyGroupRepo;
import ru.university.app.university.repo.UsersRepo;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UsersRepo usersRepo;
    @GetMapping("/success")
    public @ResponseBody String success (Principal principal){
        return "privet "+principal.getName() +"!";
    }


    @GetMapping(path="/admin/all")
    public @ResponseBody Iterable<UserUniversity> getAllUsers() {
        return usersRepo.findAll();
    }


    @GetMapping(path = "/admin/add")
    public String addForm (){
        return "/admin/add";
    }

    @PostMapping(path="/admin/add")
    public @ResponseBody String addNewUser (
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String middle_name,
            @RequestParam String surname) {

        UserUniversity user = new UserUniversity(email, name, middle_name, surname);
        usersRepo.save(user);
        return "пользователь добавлен";
    }


}
