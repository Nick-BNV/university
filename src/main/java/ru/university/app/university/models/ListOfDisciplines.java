package ru.university.app.university.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class ListOfDisciplines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserUniversity userUniversity;


    @ManyToOne(fetch = FetchType.EAGER)
    private Discipline discipline;

    public ListOfDisciplines(UserUniversity userUniversity, Discipline discipline) {
        this.userUniversity = userUniversity;
        this.discipline = discipline;
    }
}
