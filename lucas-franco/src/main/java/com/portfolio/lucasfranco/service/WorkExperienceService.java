package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.WorkExperienceEntity;

import java.util.List;
import java.util.Optional;

public interface WorkExperienceService {
    //List Work
    public List<WorkExperienceEntity> getAllWorkExperience();

    //List experience for personEntity
    public List<WorkExperienceEntity> getAllWorkExperiencePersonEntity(PersonEntity idPerson);

    //save Experience
    public WorkExperienceEntity saveWorkExperience(WorkExperienceEntity workExperienceEntity);
    public void saveWorkUpdate(WorkExperienceEntity workExperienceEntity);

    //delete work
    public void deleteWorkExperience(String id);

    //find work
    public WorkExperienceEntity findWorkExperienceById(String id);

    public boolean existsById(String id);

    public Optional<WorkExperienceEntity> getOneWorkExperience(String id);


}
