package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.Course;
import ru.university.app.university.models.Specialty;
import ru.university.app.university.models.StudyGroup;
import ru.university.app.university.service.GroupService;

@Controller
@RequestMapping(path = "/group")
public class GroupController {


    GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path="/all")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:read')")
    public Iterable<StudyGroup> getAllGroup() {
        return groupService.getAll();
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

}
