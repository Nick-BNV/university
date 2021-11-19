package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.repo.EducationalWorkRepo;
import ru.university.app.university.service.EducationalWorkService;
import ru.university.app.university.service.EducationalWorkServiceImpl;
import ru.university.app.university.service.ListOfDisciplinesServiceImpl;
import ru.university.app.university.service.UserUniversityServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/IndividualPlan")
public class IndividualPlanController {

    @Autowired
    EducationalWorkServiceImpl educationalWorkService;

    @Autowired
    UserUniversityServiceImpl userUniversityService;

    @Autowired
    ListOfDisciplinesServiceImpl listOfDisciplinesService;

    @Autowired
    ListOfDisciplinesServiceImpl disciplinesService;


    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String all(Principal principal, Model model) {
        String email = principal.getName();
        if (educationalWorkService.findByUser(email) == null)
        {model.addAttribute("iplan", educationalWorkService.findById(1L));}
          else {model.addAttribute("iplan", educationalWorkService.findByUser(email));}

    return "iplan/all";}

    @PostMapping(path = "all")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(Principal principal, Model model){

        String email = principal.getName();
        EducationalWork educationalWork = educationalWorkService.findByUser(email);
        if (educationalWork == null){}
        else { educationalWorkService.delete(educationalWork.getId());}
        all(principal, model);
        return "iplan/all";}

    @GetMapping(path = "/add")
    @PreAuthorize("hasAuthority('developers:write')")
    @ResponseBody
    public String add (
           // @RequestParam Integer lectures,
          //  @RequestParam Integer practices,
          //  @RequestParam Integer labs,
          //  @RequestParam Integer consultations,
          //  @RequestParam Integer controlWork,
         //   @RequestParam Integer courseWork,
         //   @RequestParam Integer exam,
         //    @RequestParam Integer zachet,
            Principal principal){
        String email = principal.getName();
        Iterable <UserUniversity> user = userUniversityService.getByEmail(email);
        ArrayList<UserUniversity> arList = makeCollection(user);
        Iterable<ListOfDisciplines> disList = listOfDisciplinesService.findByUserId(arList.get(0).getId());
        ArrayList<Discipline> list = new ArrayList<>();
        for (ListOfDisciplines l:
             disList) {
            list.add(l.getDiscipline());
        }
        Integer countLectures=0;
        Integer countPractices=0;
        Integer countLabs = 0;
        Integer countConsultation = 0;
        Integer countControlWork= 0;
        Integer countCourseWork= 0;
        Integer countExam= 0;
        Integer countZachet= 0;
        for (Discipline d:
             list) {
            countLectures = countLectures + d.getLectures();


        }
        return countLectures.toString();}


    public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }


}
