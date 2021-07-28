package com.example.service;



import com.example.model.Role;
import com.example.model.User;

import java.util.List;
import java.util.Set;

public interface Service {
     User getById(Long id) ;

     List<User> allUsers();

     void save(User user);

     void delete(Long id);

     void edit(User user);

     Set<Role> allRoles();

}
