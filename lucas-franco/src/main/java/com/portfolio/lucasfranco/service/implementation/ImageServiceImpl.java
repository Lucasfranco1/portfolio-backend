package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.entity.ImageEntity;
import com.portfolio.lucasfranco.repository.ImageRepository;
import com.portfolio.lucasfranco.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<ImageEntity> listImages(){
        return imageRepository.findByOrderById();
    }
    public void saveImage(ImageEntity imageEntity){
        imageRepository.save(imageEntity);
    }
    public void deleteImage(String id){
        imageRepository.deleteById(id);
    }

    public Optional<ImageEntity> getOneImage(String id){
        return  imageRepository.findById(id);
    }

    public boolean exists(String id){
        return imageRepository.existsById(id);
    }
}
