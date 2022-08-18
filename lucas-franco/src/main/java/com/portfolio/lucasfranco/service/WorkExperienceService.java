package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.dto.WorkExperienceDTO;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.WorkExperienceEntity;

import java.util.List;
import java.util.Optional;

public interface WorkExperienceService {
    //List Work
    public List<WorkExperienceEntity> getAllWorkExperience();

    //List work for personEntity
    public List<WorkExperienceEntity> getAllWorkExperiencePersonEntity(PersonEntity idPerson);

    //save Work
    public WorkExperienceEntity saveWorkExperience(WorkExperienceEntity workExperienceEntity);
    public void saveWorkUpdate(WorkExperienceEntity workExperienceEntity);

    public WorkExperienceEntity updateWork(String id, WorkExperienceDTO workExperienceDTO);

    //delete work
    public void deleteWorkExperience(String id);

    //find work
    public WorkExperienceEntity findWorkExperienceById(String id);

    public boolean existsById(String id);

    public Optional<WorkExperienceEntity> getOne(String id);


}
