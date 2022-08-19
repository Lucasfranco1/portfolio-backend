package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.ImageEntity;

import java.util.List;
import java.util.Optional;

public interface ImageService {


    public List<ImageEntity> listImages();

    public void saveImage(ImageEntity imageEntity);

    public void deleteImage(String id);

    public Optional<ImageEntity> getOneImage(String id);

    public boolean exists(String id);
}
