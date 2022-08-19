package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.EducationEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface EducationService {


    public List<EducationEntity> getAllEducation();


    public List<EducationEntity> getAllEducationPersonEntity(PersonEntity idPerson);


    public EducationEntity saveEducation(EducationEntity educationEntity);

    public void saveEducationUpdate(EducationEntity education);

    public Optional<EducationEntity> getOneEducation(String id);

    //delete edu
    public void deleteEducation(String id);



    public EducationEntity findEducationById(String id);

    public boolean existsById(String id);

}
