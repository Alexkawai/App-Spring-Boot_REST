package com.example.service;



import com.example.model.User;

import java.util.List;

public interface Service {
     User getById(Long id) ;

     List<User> allUsers();

     void save(User user);

     void delete(Long id);

     void edit(User user);

}
