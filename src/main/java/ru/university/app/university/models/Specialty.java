package ru.university.app.university.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Specialty {
    AppliedComputerScience ("09.03.03 Прикладная информатика"), RailwayСonstruction("23.05.06 Строительство железных дорог");

    private String name;
    Specialty(String name) {
        this.name=name;
    }
    public String getDisplayName() {
        return name;
    }
}
