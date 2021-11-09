package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.service.DisciplineServiceImpl;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private final DisciplineServiceImpl disciplineService;

    public DisciplineController(DisciplineServiceImpl disciplineService) {
        this.disciplineService = disciplineService;
    }



    @GetMapping(path="/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String getAllDiscipline(Model model) {
        Iterable<Discipline> iterable = disciplineService.getAll();
        model.addAttribute("discipline", iterable);
        return "/discipline/all";
    }


    @GetMapping(path = "/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String addForm (){
        return "/discipline/add";
    }


    @PostMapping(path="/add")
    @PreAuthorize("hasAuthority('developers:write')")
    @ResponseBody
    public  String add (
            @RequestParam Specialty specialty,
            @RequestParam String name,
            @RequestParam Integer lectures,
            @RequestParam Integer practices,
            @RequestParam Integer labs,
            @RequestParam Integer consultations,
            @RequestParam Boolean controlWork,
            @RequestParam Boolean courseWork,
            @RequestParam Boolean exam,
            @RequestParam Boolean zachet)
    {
        Discipline discipline = new Discipline(specialty, name, lectures, practices, labs, consultations, controlWork, courseWork, exam, zachet);
       disciplineService.save(discipline);
        return "дисциплина добавлена";
    }

    /*

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
*/


}
