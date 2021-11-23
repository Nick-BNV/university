package ru.university.app.university.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {


    USER("преподаватель",Set.of(Permission.DEVELOPERS_READ)),
    ADMIN("администратор",Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE));

    private String name;
    Role(String name, Set<Permission> permissions) {
        this.name=name;
        this.permissions = permissions;
    }
    public String getDisplayName() {
        return name;
    }

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }


}
