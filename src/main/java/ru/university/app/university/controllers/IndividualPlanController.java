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
import java.util.List;

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
        List<UserUniversity> userList = userUniversityService.getByEmail(email);
        if (userList.get(0).getEducationalWork()==null)
        {model.addAttribute("iplan", educationalWorkService.findById(1L));}
        else {model.addAttribute("iplan", educationalWorkService.findByUserEmail(email).get(0));}

        return "iplan/all";}

    @PostMapping(path = "all")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(Principal principal, Model model){

        String email = principal.getName();
        EducationalWork educationalWork = educationalWorkService.findAllByUserEmail(email).get(0);
        if (educationalWork == null){}
        else {userUniversityService.getByEmail(email).get(0).setEducationalWork(null);
            educationalWorkService.delete(educationalWork);}
        all(principal, model);
        return "iplan/all";}


    @GetMapping (path = "/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String edit (Model model, Principal principal){
        String email = principal.getName();
        List<UserUniversity> arList = userUniversityService.getByEmail(email);
        if (arList.get(0).getEducationalWork()==null){
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
            countPractices = countPractices +d.getPractices();
            countLabs = countLabs +d.getLabs();
            countConsultation = countConsultation+d.getConsultations();
            if(d.getControlWork()){countControlWork=countControlWork+1;}
            if(d.getCourseWork()){countCourseWork=countCourseWork+1;}
            if (d.getExam()){countExam=countExam+1;}
            if (d.getZachet()){countZachet=countZachet+1;}
            model.addAttribute("countLectures", countLectures);
            model.addAttribute("countPractices", countPractices);
            model.addAttribute("countLabs", countLabs);
            model.addAttribute("countConsultation", countConsultation);
            model.addAttribute("countControlWork", countControlWork);
            model.addAttribute("countCourseWork", countCourseWork);
            model.addAttribute("countExam", countExam);
            model.addAttribute("countZachet", countZachet);}
        }
        else {
            model.addAttribute("countLectures", arList.get(0).getEducationalWork().getLectures());
            model.addAttribute("countPractices", arList.get(0).getEducationalWork().getPractices());
            model.addAttribute("countLabs", arList.get(0).getEducationalWork().getLabs());
            model.addAttribute("countConsultation", arList.get(0).getEducationalWork().getConsultations());
            model.addAttribute("countControlWork", arList.get(0).getEducationalWork().getControlWork());
            model.addAttribute("countCourseWork",arList.get(0).getEducationalWork().getCourseWork());
            model.addAttribute("countExam", arList.get(0).getEducationalWork().getExam());
            model.addAttribute("countZachet", arList.get(0).getEducationalWork().getZachet());}



        return "iplan/add";}


    @PostMapping (path = "/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String add (
             @RequestParam Integer lectures,
             @RequestParam Integer practices,
             @RequestParam Integer labs,
             @RequestParam Integer consultations,
             @RequestParam Integer controlWork,
             @RequestParam Integer courseWork,
             @RequestParam Integer exam,
             @RequestParam Integer zachet,
            Principal principal){
        String email = principal.getName();
        EducationalWork educationalWork = new EducationalWork();
            UserUniversity userUniversity = userUniversityService.getByEmail(email).get(0);
            educationalWork.setLectures(lectures);
            educationalWork.setPractices(practices);
            educationalWork.setLabs(labs);
            educationalWork.setConsultations(consultations);
            educationalWork.setControlWork(controlWork);
            educationalWork.setCourseWork(courseWork);
            educationalWork.setExam(exam);
            educationalWork.setZachet(zachet);
            userUniversity.setEducationalWork(educationalWork);
            educationalWorkService.save(educationalWork);
        return "iplan/add";}


    public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
