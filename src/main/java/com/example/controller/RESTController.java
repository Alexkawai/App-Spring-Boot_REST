package com.example.controller;

import com.example.model.User;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("rest/")
public class RESTController {
    private Service service;

    @Autowired
    public void setService(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/allUsers")
    public List<User>  list(ModelMap model) {
        return service.allUsers();
    }

    @PostMapping(value = "/add" )
    public void create(@RequestBody User user ) {
        service.save(user);
    }
    @GetMapping(value = "/{id}")
    public User takeUser(@PathVariable Long id) {
        return service.getById(id);
    }

    @PatchMapping(value = "/edit")
    public void update(@RequestBody User user) {
        service.edit(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);

    }
}
