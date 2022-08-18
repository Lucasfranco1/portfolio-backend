package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.Message;
import com.portfolio.lucasfranco.dto.WorkExperienceDTO;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.WorkExperienceEntity;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@RestController
@RequestMapping(PATH_EXPERIENCE)
@CrossOrigin(origins = CROSS_ORIGIN)
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private PersonService personService;

    @PreAuthorize(ADMIN)
    @GetMapping(PATH_GET_ALL)
    public ResponseEntity<List<WorkExperienceEntity>> getAllWorkExperience(){
        List<WorkExperienceEntity> workExperienceEntityList= workExperienceService.getAllWorkExperience();
        return new ResponseEntity(workExperienceEntityList, HttpStatus.OK);

    }

    @PreAuthorize(ADMIN)
    @PostMapping(CREATE)
    public ResponseEntity<WorkExperienceEntity> savePerson(@Validated @RequestBody WorkExperienceEntity workExperienceEntity){
        WorkExperienceEntity work = workExperienceService.saveWorkExperience(workExperienceEntity);
        return new ResponseEntity<>(work, HttpStatus.CREATED);

    }
    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE)
    public ResponseEntity<WorkExperienceEntity> updateExperience(@PathVariable(ID) String id, @RequestBody WorkExperienceDTO workExperienceDTO){

        WorkExperienceEntity experience = workExperienceService.getOne(id).get();

        experience.setWork(workExperienceDTO.getWork());
        experience.setDescriptionWork(workExperienceDTO.getDescriptionWork());
        experience.setYear(workExperienceDTO.getYear());
        experience.setLogoBusinessUrl(workExperienceDTO.getLogoBusinessUrl());

        workExperienceService.saveWorkUpdate(experience);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(DETAIL_ID)
    public ResponseEntity<WorkExperienceEntity> getById(@PathVariable(ID) String id){
        if(!workExperienceService.existsById(id))
            return new ResponseEntity(new Message(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        WorkExperienceEntity experience = workExperienceService.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }


    @PreAuthorize(ADMIN)
    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteExperience(@PathVariable String id){
        workExperienceService.deleteWorkExperience(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ALL_PROFILE)
    public List<WorkExperienceEntity> getAllWorkExperienceByPersonEntity() {
        PersonEntity personEntity= personService.findPersonById(PERSON_ID);
        return workExperienceService.getAllWorkExperiencePersonEntity(personEntity);
    }


}

