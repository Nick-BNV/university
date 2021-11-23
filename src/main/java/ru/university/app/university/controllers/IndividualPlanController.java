package ru.university.app.university.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.*;
import ru.university.app.university.repo.EducationalWorkRepo;
import ru.university.app.university.service.*;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    @Autowired
    GroupServiceImpl groupService;


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
        Float countControlWork= 0f;
        Float countCourseWork= 0f;
        Float countExam= 0f;
        Float countZachet= 0f;



        for (Discipline d:
                list) {
            countLectures = countLectures + d.getLectures();
            countPractices = countPractices +d.getPractices();
            countLabs = countLabs +d.getLabs();
            countConsultation = countConsultation+d.getConsultations();


            for (StudyGroup group:groupService.findByCourseAndSpecialty(d.getCourse(),d.getSpecialty())
            ) {
                Float studentCount=0f;
                studentCount=group.getStudentCount()+studentCount;

            if(d.getControlWork()){countControlWork=studentCount*0.3f+countControlWork;}
            if (d.getCourseWork()){countCourseWork=studentCount*1f+countCourseWork;}
            if (d.getExam()){countExam=studentCount*0.35f+countExam;}
            if (d.getZachet()){countZachet=studentCount*0.25f+countZachet;}}
            }



            model.addAttribute("countLectures", countLectures);
            model.addAttribute("countPractices", countPractices);
            model.addAttribute("countLabs", countLabs);
            model.addAttribute("countConsultation", countConsultation);
            model.addAttribute("countControlWork", countControlWork);
            model.addAttribute("countCourseWork", countCourseWork);
            model.addAttribute("countExam", countExam);
            model.addAttribute("countZachet", countZachet);
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
             @RequestParam Float controlWork,
             @RequestParam Float courseWork,
             @RequestParam Float exam,
             @RequestParam Float zachet,
            Principal principal,
             Model model){
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
            all(principal, model);
        return "/iplan/all";}


    public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
