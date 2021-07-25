package com.example.service;



import com.example.model.Role;
import com.example.model.User;

import java.util.List;
import java.util.Set;

public interface Service {
     User getById(Long id) ;

     List<User> allUsers();

     void save(User user, String[] role);

     void delete(Long id);

     void edit(User user,String[] role);

     Set<Role> allRoles();

}
