package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.ImageEntity;

import java.util.List;
import java.util.Optional;

public interface ImageService {


    public List<ImageEntity> list();

    public void save(ImageEntity imageEntity);

    public void delete(String id);

    public Optional<ImageEntity>getOne(String id);

    public boolean exists(String id);
}
