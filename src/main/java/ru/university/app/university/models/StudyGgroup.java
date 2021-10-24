package ru.university.app.university.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class StudyGgroup {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String groupName;

    private Integer studentCount;

    public StudyGgroup(String groupName, Integer studentCount) {
        this.groupName = groupName;
        this.studentCount=studentCount;

    }
}
