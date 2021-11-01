package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping("/update/{id}")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userUniversityService.getUser(id));

        return "admin/update";
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
