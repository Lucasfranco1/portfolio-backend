package com.portfolio.lucasfranco.authentication.repository;

import com.portfolio.lucasfranco.authentication.entity.RolEntity;
import com.portfolio.lucasfranco.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, String> {
    Optional<RolEntity> findByRoles(Roles roles);
}
