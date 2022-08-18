package com.portfolio.lucasfranco.authentication.service;

import com.portfolio.lucasfranco.authentication.entity.UserEntity;
import com.portfolio.lucasfranco.authentication.entity.UserPrincipal;
import com.portfolio.lucasfranco.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsCustomService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nameUser) throws UsernameNotFoundException {
        UserEntity user = userService.getByNameUser(nameUser).get();
        return UserPrincipal.build(user);
    }
}
