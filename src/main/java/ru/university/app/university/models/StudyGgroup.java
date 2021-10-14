package ru.university.app.university.models;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class StudyGgroup {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "groupName")
    private String groupName;


    public StudyGgroup(){}

    public StudyGgroup(String groupName) {
        this.groupName = groupName;

    }
}
