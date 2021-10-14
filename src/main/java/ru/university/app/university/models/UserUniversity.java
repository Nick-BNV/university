package ru.university.app.university.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class UserUniversity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "surname")
    private String surname;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public UserUniversity() {}

    public UserUniversity(String email,  String name, String middle_name, String surname) {
        this.email = email;
        this.name = name;
        this.middle_name = middle_name;
        this.surname = surname;
    }


}
