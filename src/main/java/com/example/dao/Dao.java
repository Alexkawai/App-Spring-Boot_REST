package com.example.dao;

import com.example.model.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface Dao {
     void save(User user) ;
    User getById(Long id) ;
    void delete(Long id) ;
    List<User> allUsers() ;
    void edit(User user) ;

    UserDetails loadUserByUsername(String email) ;
}
