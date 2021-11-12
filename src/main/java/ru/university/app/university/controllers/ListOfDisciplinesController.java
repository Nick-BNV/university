package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.service.ListOfDisciplinesService;
import ru.university.app.university.service.ListOfDisciplinesServiceImpl;

@Controller
@RequestMapping("/ListOfDisciplines")
public class ListOfDisciplinesController {
    @Autowired
    ListOfDisciplinesServiceImpl listOfDisciplinesServiceImpl;

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
            @RequestParam UserUniversity user,
            @RequestParam Discipline discipline) {

        ListOfDisciplines l =new ListOfDisciplines(user, discipline);
        listOfDisciplinesServiceImpl.save(l);
        return "группа добавлена";
    }

}
