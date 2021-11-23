package ru.university.app.university.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class EducationalWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer lectures;

    private Integer practices;

    private Integer labs;

    private  Integer consultations;

    private Float controlWork;

    private Float courseWork;

    private Float exam;

    private Float zachet;


    @OneToOne (mappedBy = "educationalWork")
    private UserUniversity userUniversity;

    public EducationalWork(Integer lectures, Integer practices, Integer labs, Integer consultations, Float controlWork, Float courseWork, Float exam, Float zachet, UserUniversity userUniversity) {
        this.lectures = lectures;
        this.practices = practices;
        this.labs = labs;
        this.consultations = consultations;
        this.controlWork = controlWork;
        this.courseWork = courseWork;
        this.exam = exam;
        this.zachet = zachet;
        this.userUniversity = userUniversity;
    }
}
