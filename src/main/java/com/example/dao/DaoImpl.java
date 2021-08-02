package com.example.dao;


import com.example.model.Role;
import com.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DaoImpl implements Dao{

    @PersistenceContext
    private EntityManager         em ;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DaoImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        em.merge(user);
    }

    @Override
    public User getById(Long id) {

        return em.createQuery("select distinct u from User u left join fetch u.roles where u.id=:id",User.class)
                .setParameter("id",id).getSingleResult();
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class,id);
        em.remove(user);
    }
    @Override
    public List<User> allUsers() {
        return em.createQuery("select distinct u from User u left join fetch u.roles",User.class).getResultList();
    }

    @Override
    public void edit(User user) {
        User user2=em.find(User.class, user.getId());
        user2.setId(user.getId());
        user2.setName(user.getName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user2.setRoles(user.getRoles());

        em.merge(user2);
    }
    @Override
    public User loadUserByUsername(String email) {
        User user = em.createQuery("select distinct u from User u left join fetch u.roles where u.email = :email ",User.class)
                .setParameter("email",email).getSingleResult();
        return user;
    }
    @Override
    public Set<Role> allRoles(){
        return em.createQuery("from Role ",Role.class).getResultList().stream().collect(Collectors.toSet());
    }

}
