package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.Message;
import com.portfolio.lucasfranco.dto.MessageCloudinary;
import com.portfolio.lucasfranco.dto.PersonDTO;
import com.portfolio.lucasfranco.entity.ImageEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.exceptions.ParamNotFound;
import com.portfolio.lucasfranco.service.ImageService;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.implementation.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@RestController
@RequestMapping(PATH_PERSON)
@CrossOrigin(origins = CROSS_ORIGIN)
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;


    @GetMapping(PATH_GET_ALL)
    public List<PersonEntity> getPersons(){
        return personService.getPerson();
    }


    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE)
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable(ID) String id, @RequestBody PersonDTO personDTO){

        PersonEntity person = personService.findPersonById(id);

        person.setNameFirst(personDTO.getNameFirst());
        person.setLastName(personDTO.getLastName());
        person.setProfession(personDTO.getProfession());
        person.setAbout(personDTO.getAbout());
        person.setImage(personDTO.getImage());
        person.setBannerImage(personDTO.getBannerImage());
        person.setCountry(personDTO.getCountry());
        person.setProvince(personDTO.getProvince());
        person.setGitHubUrl(personDTO.getGitHubUrl());
        person.setLinkedinUrl(personDTO.getLinkedinUrl());
        person.setCurriculum(personDTO.getCurriculum());

        personService.savePersonUpdate(person);
        return new ResponseEntity(HttpStatus.OK);

    }
    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE_PHOTO)
    public ResponseEntity<?>uploadPhotoInCloudinary(@RequestParam(PARAM_FILE) MultipartFile multipartFile, @PathVariable String id) throws IOException {
        PersonEntity person = personService.findPersonById(id);
        if(person != null){
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                return new ResponseEntity(new MessageCloudinary(ERROR_IMAGE), HttpStatus.BAD_REQUEST);
            }
            Map result = cloudinaryService.upload(multipartFile);
            ImageEntity imageEntity =
                    new ImageEntity((String) result.get(ORIGINAL_FILENAME),
                            (String) result.get(URL),
                            (String) result.get(PUBLIC_ID));
            imageService.saveImage(imageEntity);
            person.setImage(imageEntity.getImageUrl());
            personService.savePerson(person);
        }else{
            throw new ParamNotFound(ERROR_BY_ID);
        }

        return new ResponseEntity(new MessageCloudinary(IMAGE_UPLOAD), HttpStatus.OK);
    }


    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE_BANNER)
    public ResponseEntity<?>uploadBannerInCloudinary(@RequestParam(PARAM_FILE) MultipartFile multipartFile, @PathVariable String id) throws IOException {
        PersonEntity person = personService.findPersonById(id);
        if(person != null){
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null) {
                return new ResponseEntity(new MessageCloudinary(ERROR_IMAGE), HttpStatus.BAD_REQUEST);
            }
            Map result = cloudinaryService.upload(multipartFile);
            ImageEntity imageEntity =
                    new ImageEntity((String) result.get(ORIGINAL_FILENAME),
                            (String) result.get(URL),
                            (String) result.get(PUBLIC_ID));

            person.setBannerImage(imageEntity.getImageUrl());
            personService.savePerson(person);
        }else{
            throw new ParamNotFound(ERROR_BY_ID);
        }

        return new ResponseEntity(new MessageCloudinary(IMAGE_UPLOAD), HttpStatus.OK);
    }

    @GetMapping
    public PersonEntity findPerson(){
        return personService.findPersonById(PERSON_ID);
    }

    @GetMapping(DETAIL_ID)
    public ResponseEntity<PersonEntity> getByIdPerson(@PathVariable(ID) String id){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        PersonEntity person = personService.findPersonById(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }


}
