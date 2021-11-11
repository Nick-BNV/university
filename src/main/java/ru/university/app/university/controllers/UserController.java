package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.service.UserUniversityService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserUniversityService userUniversityService;

    @Autowired
    public UserController(UserUniversityService userUniversityService){
        this.userUniversityService = userUniversityService;
    }


    @GetMapping(path="/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String getAllUsers(Model model) {
        Iterable<UserUniversity> iterable = userUniversityService.getAllUsers();
        model.addAttribute("user", iterable);
        return "/admin/all";
    }

    @PostMapping(path = "/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findBySurname (Model model,
                                 @RequestParam String filter){
        Iterable<UserUniversity> iterable = userUniversityService.findBySurname(filter);
        model.addAttribute("user", iterable);
        return "/admin/all";
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
            @RequestParam String surname,
            @RequestParam Role role,
            @RequestParam Status status
            ) {

        UserUniversity user = new UserUniversity(email, name, middle_name, surname, role, status);
        userUniversityService.saveUser(user);
        return "пользователь добавлен";
    }

    @GetMapping(path = "/details/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public String userDetails (@PathVariable(value = "id") Long id, Model model){
        if(userUniversityService.existById(id)){
            ArrayList<UserUniversity> user = userUniversityService.details(id);
            model.addAttribute("user", user);
        }
        else {
            getAllUsers(model);
            return "/admin/all";}
        return "/admin/details";
    }

    @GetMapping(path = "/details/{id}/edit")
    @PreAuthorize("hasAuthority('developers:write')")
    public String userEdit (@PathVariable(value = "id") Long id, Model model){
        if(userUniversityService.existById(id)){
            ArrayList<UserUniversity> user = userUniversityService.details(id);
            model.addAttribute("user", user);
        }
        else {
            getAllUsers(model);
            return "/admin/all";}
        return "/admin/edit";
    }

    @PostMapping(path="/details/{id}/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String editUser (@PathVariable(value = "id") Long id,
                             @RequestParam String surname,
                             @RequestParam String name,
                             @RequestParam String middle_name,
                             @RequestParam String email,
                             @RequestParam Status status,
                             @RequestParam Role role) {
        UserUniversity user = userUniversityService.getUserById(id);
        user.setSurname(surname);
        user.setName(name);
        user.setMiddle_name(middle_name);
        user.setEmail(email);
        user.setStatus(status);
        user.setRole(role);
        userUniversityService.saveUser(user);
        return "Данные обновлены";
    }

    @PostMapping(path="/details/{id}/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String deleteUser (@PathVariable(value = "id") Long id) {
        userUniversityService.deleteUser(id);
        return "Пользователь удален";
    }



}
