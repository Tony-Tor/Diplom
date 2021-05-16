package com.example.diplom.model.web;

import com.example.diplom.model.Role;
import com.example.diplom.model.User;

import java.util.Set;

public class UserTo {
    String name;
    Long id;
    Set<Role> roles;

    public UserTo(User user) {
        this.name = user.getName();
        this.id = user.getId();
        this.roles = user.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
