package com.example.dao;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Set;

public interface Dao {
     void save(User user) ;
    User getById(Long id) ;
    void delete(Long id) ;
    List<User> allUsers() ;
    void edit(User user) ;
    Set<Role> allRoles() ;

    UserDetails loadUserByUsername(String email) ;
}
