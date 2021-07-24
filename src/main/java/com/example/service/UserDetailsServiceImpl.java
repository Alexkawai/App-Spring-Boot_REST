package com.example.service;


import com.example.dao.Dao;
import com.example.dao.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Dao dao;
    @Autowired
    public UserDetailsServiceImpl(Dao dao) {
        this.dao = dao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user =  dao.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


}
