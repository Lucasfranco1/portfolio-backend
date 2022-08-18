package com.portfolio.lucasfranco.authentication.service;

import com.portfolio.lucasfranco.authentication.entity.RolEntity;
import com.portfolio.lucasfranco.authentication.repository.RolRepository;
import com.portfolio.lucasfranco.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RolRepository rolRepository;

    public Optional<RolEntity> getByRole(Roles roles){
        return rolRepository.findByRoles(roles);
    }

    public void save(RolEntity rol){
        rolRepository.save(rol);
    }
}
