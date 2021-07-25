package com.example.dao;


import com.example.model.Role;
import com.example.model.User;
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
    private EntityManager  em ;

    private Set<Role> getRolesFromBD(String[] role){
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_USER")) {
                rol.add(em.find(Role.class,1L));
            } else {
                rol.add(em.find(Role.class,2L));
            }
        }
        return rol;
    }

    @Override
    public void save(User user,String[] role) {
        user.setRoles(getRolesFromBD(role));
        em.merge(user);
    }
    @Override
    public User getById(Long id) {
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
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
    public void edit(User user,String[] role) {
        User user2=em.find(User.class, user.getId());
        user2.setId(user.getId());
        user2.setName(user.getName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setRoles(getRolesFromBD(role));

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
