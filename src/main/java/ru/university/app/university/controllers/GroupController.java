package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.StudyGgroup;
import ru.university.app.university.repo.StudyGroupRepo;

@Controller
@RequestMapping(path = "/group")
public class GroupController {
    @Autowired
    private StudyGroupRepo studyGroupRepo;

    @GetMapping(path="/allgroup")
    public @ResponseBody
    Iterable<StudyGgroup> getAllGroup() {
        return studyGroupRepo.findAll();
    }

    @GetMapping(path = "/addgroup")
    public String addgroup (){
        return "group/addgroup";
    }

    @PostMapping(path="/addgroup")
    public @ResponseBody String addnewgroup (
            @RequestParam String group_name) {

        StudyGgroup s = new StudyGgroup(group_name);
        studyGroupRepo.save(s);
        return "группа добавлена";
    }

}
