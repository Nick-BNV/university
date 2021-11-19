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


    @GetMapping (path = "/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String edit (Model model, Principal principal){
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
            model.addAttribute("countZachet", countZachet);
        }
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
        EducationalWork educationalWork;
        if(educationalWorkService.findByUser(email)==null){educationalWork = new EducationalWork();}
       else {educationalWork=educationalWorkService.findByUser(email);}
            UserUniversity userUniversity = userUniversityService.getByEmail(email).get(0);

            educationalWork.setLectures(lectures);
            educationalWork.setPractices(practices);
            educationalWork.setLabs(labs);
            educationalWork.setConsultations(consultations);
            educationalWork.setControlWork(controlWork);
            educationalWork.setCourseWork(courseWork);
            educationalWork.setExam(exam);
            educationalWork.setZachet(zachet);
            // передает весь объект вместо индекса, в результате в save не передается
            educationalWork.setUserUniversity(userUniversity);
            System.out.println(educationalWork.getUserUniversity());
        // UserUniversity(id=1, email=bugreev.n@yandex.ru, pass=$2a$12$2tPyiIymSmo9QtKbxyGBS.Qi056KRdsj/zLgrGNvpELQ7hixOFex2, name=Николай, middle_name=Викторович, surname=Бугреев, status=ACTIVE, role=ADMIN, list=[], educationalWork=null)
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
