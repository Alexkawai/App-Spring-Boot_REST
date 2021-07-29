package com.example.controller;

import com.example.model.User;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> list() {
        return new ResponseEntity(service.allUsers(), HttpStatus.FOUND);
    }

    @PostMapping(value = "/add" )
    public ResponseEntity<Void> create(@RequestBody User user ) {
        service.save(user);
        return new ResponseEntity( HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> takeUser(@PathVariable Long id) {
        return new ResponseEntity(service.getById(id), HttpStatus.FOUND);
    }

    @PatchMapping(value = "/edit")
    public ResponseEntity<Void> update(@RequestBody User user) {
        service.edit(user);
        return new ResponseEntity( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity( HttpStatus.OK);
    }
}
