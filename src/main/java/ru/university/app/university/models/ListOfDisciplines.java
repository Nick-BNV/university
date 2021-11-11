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
    @JoinColumn(name = "user_id")
    private UserUniversity userUniversity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dis_id")
    private Discipline discipline;


}
