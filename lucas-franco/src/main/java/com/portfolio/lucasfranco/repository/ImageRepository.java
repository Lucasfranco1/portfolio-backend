package com.portfolio.lucasfranco.repository;

import com.portfolio.lucasfranco.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {

    List<ImageEntity> findByOrderById();
}
