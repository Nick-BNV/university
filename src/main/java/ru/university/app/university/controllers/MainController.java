package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.service.UserUniversityService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class MainController {

    private final UserUniversityService userUniversityService;

    @Autowired
    public MainController(UserUniversityService userUniversityService){
        this.userUniversityService = userUniversityService;
    }

    @GetMapping("/success")
    @PreAuthorize("hasAuthority('developers:read')")
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


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    @ResponseBody
    public String showUser(@PathVariable(value = "id") int id) {

        return null;
    }
/*
    @PostMapping(value = "/update/{id}")
    public String update(@ModelAttribute("user") @Valid UserUniversity userUniversity, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        if (bindingResult.hasErrors()) {
            return "admin/update";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomerForm(@PathVariable(name = "id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
*/

}
