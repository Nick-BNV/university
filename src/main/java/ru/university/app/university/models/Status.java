package ru.university.app.university.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Status {
    ACTIVE("активен"), BANNED("заблокирован");
    private String name;

    public String getDisplayName() {
        return name;
    }

    private Status (String name){
        this.name=name;
    }
}
