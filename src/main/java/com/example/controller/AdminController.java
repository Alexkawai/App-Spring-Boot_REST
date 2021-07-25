package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("")
public class AdminController {

    private Service service;

    @Autowired
    public void setService(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/admin")
    public String printUser(ModelMap model) {

        List<User> users= service.allUsers();
        model.addAttribute("roles", service.allRoles());
        model.addAttribute("users", users);
        model.addAttribute("addUser", new User());
        return "index";
    }

    /*@GetMapping(value = "/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }*/


    @PostMapping(value = "/admin/add" )
    public String create( User user ,
                          @RequestParam(value = "select_role", required = false) String[] role) {
        service.save(user,role);
        return "redirect:/admin";
    }
    @GetMapping(value = "/user")
    public String takeUser(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login( ) {
        return "redirect:/user";
    }
    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "select_roles", required = false) String[] role) {

        service.edit(user, role);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/admin";
    }

}
