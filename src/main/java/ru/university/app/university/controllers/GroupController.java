package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.StudyGgroup;
import ru.university.app.university.repo.StudyGroupRepo;
import ru.university.app.university.service.GroupService;

@Controller
@RequestMapping(path = "/group")
public class GroupController {
    //@Autowired
    //private StudyGroupRepo studyGroupRepo;

    GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path="/all")
    @ResponseBody
    @PreAuthorize("hasAuthority('developers:read')")
    public Iterable<StudyGgroup> getAllGroup() {
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
            @RequestParam Integer studentCount) {

        StudyGgroup s = new StudyGgroup(group_name, studentCount);
        groupService.saveGroup(s);
        return "группа добавлена";
    }

}
