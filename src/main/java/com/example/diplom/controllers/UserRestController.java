package com.example.diplom.controllers;

import com.example.diplom.model.User;
import com.example.diplom.model.web.UserTo;
import com.example.diplom.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('ADMIN')")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserTo get(@PathVariable("id") Integer id){
        return new UserTo(service.get(id));
    }

    @GetMapping("/all")
    public List<UserTo> getAll(){
        return service.getAll().stream().map(UserTo::new).collect(Collectors.toList());
    }

}
