package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.service.DisciplineServiceImpl;

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



    @GetMapping(path = "/details/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public String details (@PathVariable(value = "id") Long id, Model model){
        if(disciplineService.existById(id)){
            ArrayList<Discipline> array = disciplineService.details(id);
            model.addAttribute("array", array);
        }
        else {
            getAllDiscipline(model);
            return "/discipline/all";}
        return "/discipline/details";
    }

    @GetMapping(path = "/details/{id}/edit")
    @PreAuthorize("hasAuthority('developers:write')")
    public String edit (@PathVariable(value = "id") Long id, Model model){
        if(disciplineService.existById(id)){
            ArrayList<Discipline> array = disciplineService.details(id);
            model.addAttribute("array", array);
        }
        else {
            getAllDiscipline(model);
            return "/discipline/all";}
        return "/discipline/edit";
    }

    @PostMapping(path="/details/{id}/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String edit (@PathVariable(value = "id") Long id,
                             @RequestParam Specialty specialty,
                             @RequestParam String name,
                             @RequestParam Integer lectures,
                             @RequestParam Integer practices,
                             @RequestParam Integer labs,
                             @RequestParam Integer consultations,
                             @RequestParam Boolean controlWork,
                             @RequestParam Boolean courseWork,
                             @RequestParam Boolean exam,
                             @RequestParam Boolean zachet) {
        Discipline discipline = disciplineService.getDiscipline(id);
        discipline.setSpecialty(specialty);
        discipline.setName(name);
        discipline.setLectures(lectures);
        discipline.setPractices(practices);
        discipline.setLabs(labs);
        discipline.setConsultations(consultations);
        discipline.setControlWork(controlWork);
        discipline.setCourseWork(courseWork);
        discipline.setExam(exam);
        discipline.setZachet(zachet);
        disciplineService.save(discipline);
        return "Данные обновлены";
    }

    @PostMapping(path="/details/{id}/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String delete (@PathVariable(value = "id") Long id) {
        disciplineService.deleteDiscipline(id);
        return "Пользователь удален";
    }

    @GetMapping(path = "/addUserDis")
    @PreAuthorize("hasAuthority('developers:write')")
    public  String addUserDis (){
        return "/admin/addUserDis";
    }

}
