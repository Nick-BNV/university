package ru.university.app.university.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class EducationalWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer lectures;

    private Integer practices;

    private Integer labs;

    private  Integer consultations;

    private Integer controlWork;

    private Integer courseWork;

    private Integer exam;

    private Integer zachet;


    @OneToOne(mappedBy = "educationalWork",fetch = FetchType.EAGER)
    private UserUniversity userUniversity;


}
