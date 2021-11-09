package ru.university.app.university.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Discipline {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Specialty specialty;

    private String name;

    private Integer lectures;

    private Integer practices;

    private Integer labs;

    private  Integer consultations;

    private Boolean controlWork = false;

    private Boolean courseWork = false;

    private Boolean exam = false;

    private  Boolean zachet = false;

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
