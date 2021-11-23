package ru.university.app.university.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Course {
    FIRST("первый курс"),

    SECOND("второй курс"),

    THIRD("третий курс"),

    FOURTH("четвертый курс"),

    FIFTH("пятый курс"),

    SIXTH("шестой курс");

    private String name;

    public String getDisplayName() {
        return name;
    }

    private Course (String name){
        this.name=name;
    }

}
