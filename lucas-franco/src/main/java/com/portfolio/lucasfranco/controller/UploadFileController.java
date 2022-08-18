package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.MessageCloudinary;
import com.portfolio.lucasfranco.entity.ImageEntity;

import com.portfolio.lucasfranco.entity.ProjectEntity;
import com.portfolio.lucasfranco.service.*;
import com.portfolio.lucasfranco.service.implementation.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;


@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = CROSS_ORIGIN)
public class UploadFileController {


    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonService personService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ProjectService projectService;


    @GetMapping(PATH_LIST)
    public ResponseEntity<List<ImageEntity>>listImage(){
        List<ImageEntity> entities= imageService.list();
        return new ResponseEntity(entities, HttpStatus.OK);
    }

    @PostMapping(PATH_FILE)
    public ResponseEntity<?>uploadInCloudinary(@RequestParam(PARAM_FILE) MultipartFile multipartFile) throws IOException {
        BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
        if(bufferedImage==null){
            return new ResponseEntity(new MessageCloudinary(ERROR_IMAGE), HttpStatus.BAD_REQUEST);
        }
        Map result= cloudinaryService.upload(multipartFile);
        ImageEntity imageEntity=
                new ImageEntity((String)result.get(ORIGINAL_FILENAME),
                        (String)result.get(URL),
                        (String)result.get(PUBLIC_ID));
        imageService.save(imageEntity);
        return new ResponseEntity(new MessageCloudinary(IMAGE_UPLOAD), HttpStatus.OK);
    }
    @PostMapping(PATH_FILE_PROJECT)
    public ResponseEntity<?>uploadInCloudinaryImageProjects(@RequestParam(PARAM_FILE) MultipartFile multipartFile,
                                                            @PathVariable String id) throws IOException {
        BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
        if(bufferedImage==null){
            return new ResponseEntity(new MessageCloudinary(ERROR_IMAGE), HttpStatus.BAD_REQUEST);
        }
        Map result= cloudinaryService.upload(multipartFile);
        ImageEntity imageEntity=
                new ImageEntity((String)result.get(ORIGINAL_FILENAME),
                        (String)result.get(URL),
                        (String)result.get(PUBLIC_ID));
        imageService.save(imageEntity);
        ProjectEntity projectEntity= projectService.findProjectById(id);
        projectEntity.setImageProject(imageEntity.getImageUrl());
        projectService.saveProject(projectEntity);
        return new ResponseEntity(new MessageCloudinary(IMAGE_UPLOAD), HttpStatus.OK);
    }
    @PutMapping(UPDATE)
    public ResponseEntity<?> updateImageInCloudinary(@PathVariable String id, @RequestParam(PARAM_FILE) MultipartFile newMultipartFile ) throws IOException {
        BufferedImage bufferedImage= ImageIO.read(newMultipartFile.getInputStream());
        if(bufferedImage==null){
            return new ResponseEntity(new MessageCloudinary(ERROR_IMAGE), HttpStatus.BAD_REQUEST);
        }
        Map result= cloudinaryService.upload(newMultipartFile);
        ImageEntity imageEntity= imageService.getOne(id).get();

        imageEntity.setName((String) result.get(ORIGINAL_FILENAME));
        imageEntity.setImageUrl((String) result.get(URL));
        imageEntity.setImageId((String) result.get(PUBLIC_ID));
        imageService.save(imageEntity);
        return new ResponseEntity(new MessageCloudinary(IMAGE_UPLOAD), HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?>deleteImageInCloudinary(@PathVariable(ID) String id) throws IOException {
        if(!imageService.exists(id)){
            return new ResponseEntity(new MessageCloudinary(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        }
        ImageEntity imageEntity= imageService.getOne(id).get();
        Map result= cloudinaryService.delete(imageEntity.getImageId());
        imageService.delete(id);
        return new ResponseEntity(new MessageCloudinary("Image deleted!"), HttpStatus.OK);
    }

}
