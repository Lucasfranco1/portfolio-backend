package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.EducationDTO;
import com.portfolio.lucasfranco.dto.Message;
import com.portfolio.lucasfranco.entity.EducationEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.exceptions.MyException;
import com.portfolio.lucasfranco.service.EducationService;
import com.portfolio.lucasfranco.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@RestController
@RequestMapping(PATH_EDUCATION)
@CrossOrigin(origins = CROSS_ORIGIN)
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private PersonService personService;


    @GetMapping(PATH_GET_ALL)
    public List<EducationEntity>getAllEducations(){
        return educationService.getAllEducation();
    }

    @PreAuthorize(ADMIN)
    @PostMapping(CREATE)
    public ResponseEntity<EducationEntity> saveEducation(@Validated @RequestBody EducationEntity educationEntity){
        EducationEntity education = educationService.saveEducation(educationEntity);
        return new ResponseEntity<>(education, HttpStatus.CREATED);
    }

    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE)
    public ResponseEntity<EducationEntity> updateEducation(@PathVariable(ID) String id, @RequestBody EducationDTO educationDTO){

        EducationEntity education = educationService.getOneEducation(id).get();

        education.setEducation(educationDTO.getEducation());
        education.setDescriptionEducation(educationDTO.getDescriptionEducation());
        education.setYear(educationDTO.getYear());
        education.setLogoEducationUrl(educationDTO.getLogoEducationUrl());

        educationService.saveEducationUpdate(education);

        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(DETAIL_ID)
    public ResponseEntity<EducationEntity> getByIdEducation(@PathVariable(ID) String id){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        EducationEntity education = educationService.getOneEducation(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PreAuthorize(ADMIN)
    @DeleteMapping(DELETE_EDUCATION)
    public ResponseEntity<?> deleteEducation(@PathVariable String id){
        educationService.deleteEducation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<EducationEntity>getAllEducationsByProfile() {
        PersonEntity personEntity= personService.findPersonById(PERSON_ID);
        return educationService.getAllEducationPersonEntity(personEntity);

    }

}
