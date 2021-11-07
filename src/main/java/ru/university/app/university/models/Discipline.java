package ru.university.app.university.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    private boolean controlWork;

    private boolean courseWork;

    private boolean exam;

    private  boolean zachet;


}
