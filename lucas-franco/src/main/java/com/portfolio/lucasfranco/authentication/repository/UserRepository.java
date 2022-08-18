package com.portfolio.lucasfranco.authentication.repository;

import com.portfolio.lucasfranco.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByNameUser(String nameUser);

    boolean existsByNameUser(String nameUser);
    boolean existsByEmail(String email);

}
