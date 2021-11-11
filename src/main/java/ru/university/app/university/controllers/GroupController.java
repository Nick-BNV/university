package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.Course;
import ru.university.app.university.models.Specialty;
import ru.university.app.university.models.StudyGroup;
import ru.university.app.university.service.GroupService;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/group")
public class GroupController {


    GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path="/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public String getAllGroup(Model model) {
        Iterable<StudyGroup> iterable = groupService.getAll();
        model.addAttribute("group", iterable);
        return "/group/all";
    }

    @PostMapping(path="/all")
    @PreAuthorize("hasAnyAuthority('developers:read')")
    public String filter(Model model, @RequestParam String filter){
        Iterable<StudyGroup> iterable = groupService.findByName(filter);
        model.addAttribute("group", iterable);
        return "/group/all";
    }


    @GetMapping(path = "/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String addGroup (){
        return "group/add";
    }

    @PostMapping(path="/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String addNewGroup (
            @RequestParam String group_name,
            @RequestParam Integer studentCount,
            @RequestParam Course course,
            @RequestParam Specialty specialty) {

        StudyGroup s = new StudyGroup(group_name, studentCount, course, specialty);
        groupService.saveGroup(s);
        return "группа добавлена";
    }

    @GetMapping(path = "/exist/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    @ResponseBody
    public String exist(@PathVariable(value = "id") Long id, Model model){
        String st = String.valueOf(groupService.existById(id));
        return st;
    }


    @GetMapping(path = "/details/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public String groupDetails (@PathVariable(value = "id") Long id, Model model){
        if(groupService.existById(id)){
            ArrayList<StudyGroup> group = groupService.details(id);
            model.addAttribute("group", group);
        }
        else {
            getAllGroup(model);
            return "/group/all";}
    return "/group/details";
    }

    @GetMapping(path = "/details/{id}/edit")
    @PreAuthorize("hasAuthority('developers:write')")
    public String groupEdit (@PathVariable(value = "id") Long id, Model model){
        if(groupService.existById(id)){
            ArrayList<StudyGroup> group = groupService.details(id);
            model.addAttribute("group", group);
        }
        else {
            getAllGroup(model);
            return "/group/all";}
        return "/group/edit";
    }

    @PostMapping(path="/details/{id}/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String editGroup (@PathVariable(value = "id") Long id,
            @RequestParam String group_name,
            @RequestParam Integer studentCount,
            @RequestParam Course course,
            @RequestParam Specialty specialty) {

        StudyGroup studyGroup = groupService.getGroup(id);
        studyGroup.setGroupName(group_name);
        studyGroup.setStudentCount(studentCount);
        studyGroup.setCourse(course);
        studyGroup.setSpecialty(specialty);
        groupService.saveGroup(studyGroup);
        return "Данные обновлены";
    }

    @PostMapping(path="/details/{id}/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:write')")
    public  String deleteGroup (@PathVariable(value = "id") Long id) {
        groupService.deleteGroup(id);
        return "Группа удалена";
    }


}
