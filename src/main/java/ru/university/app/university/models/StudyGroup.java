package ru.university.app.university.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class StudyGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String groupName;

    private Integer studentCount;

    @Enumerated(value = EnumType.STRING)
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private Specialty specialty;

    public StudyGroup(String groupName, Integer studentCount, Course course, Specialty specialty) {
        this.groupName = groupName;
        this.studentCount=studentCount;

    }
}
