package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.EducationEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface EducationService {

    //List Education
    public List<EducationEntity> getAllEducation();

    //List education the one personEntity
    public List<EducationEntity> getAllEducationPersonEntity(PersonEntity idPerson);

    //save Education
    public EducationEntity saveEducation(EducationEntity educationEntity);

    public void saveEducationUpdate(EducationEntity education);

    public Optional<EducationEntity> getOne(String id);

    //delete edu
    public void deleteEducation(String id);


    //find education
    public EducationEntity findEducationById(String id);

    public boolean existsById(String id);

}
