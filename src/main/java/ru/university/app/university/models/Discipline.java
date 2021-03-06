package ru.university.app.university.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Discipline {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Specialty specialty;

    @Enumerated(value = EnumType.STRING)
    private Course course;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    List<ListOfDisciplines> list;

    private String name;

    private Integer lectures;

    private Integer practices;

    private Integer labs;

    private  Integer consultations;

    @ColumnDefault("0")
    @Column(columnDefinition = "BOOLEAN")
    private Boolean controlWork;

    @ColumnDefault("0")
    @Column(columnDefinition = "BOOLEAN")
    private Boolean courseWork;

    @ColumnDefault("0")
    @Column(columnDefinition = "BOOLEAN")
    private Boolean exam;

    @ColumnDefault("0")
    @Column(columnDefinition = "BOOLEAN")
    private  Boolean zachet;

    public Discipline(Specialty specialty, Course course, String name, Integer lectures, Integer practices, Integer labs, Integer consultations, Boolean controlWork, Boolean courseWork, Boolean exam, Boolean zachet) {
        this.specialty = specialty;
        this.course = course;
        this.name = name;
        this.lectures = lectures;
        this.practices = practices;
        this.labs = labs;
        this.consultations = consultations;
        this.controlWork = controlWork;
        this.courseWork = courseWork;
        this.exam = exam;
        this.zachet = zachet;
    }

    public Discipline(Specialty specialty, String name, Integer lectures, Integer practices, Integer labs, Integer consultations, Boolean controlWork, Boolean courseWork, Boolean exam, Boolean zachet) {
        this.specialty = specialty;
        this.name = name;
        this.lectures = lectures;
        this.practices = practices;
        this.labs = labs;
        this.consultations = consultations;
        this.controlWork = controlWork;
        this.courseWork = courseWork;
        this.exam = exam;
        this.zachet = zachet;
    }
}
