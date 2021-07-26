package com.example.service;

import com.example.dao.Dao;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    private Dao dao;

    @Autowired
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return dao.getById(id);
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return dao.allUsers();
    }

    @Transactional
    @Override
    public void save(User user, String[] role) {
        dao.save(user, role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user,String[] role) {
        dao.edit(user,role);
    }

    @Override
    public Set<Role> allRoles() {
        return dao.allRoles();
    }


}
