package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.dto.WorkExperienceDTO;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.WorkExperienceEntity;
import com.portfolio.lucasfranco.exceptions.ParamBadRequest;
import com.portfolio.lucasfranco.repository.WorkExperienceRepository;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.PERSON_ID;

@Service
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private PersonService personService;

    @Override
    public List<WorkExperienceEntity> getAllWorkExperience() {
        List<WorkExperienceEntity>entities=workExperienceRepository.findAll();
        return entities;
    }

    @Override
    public List<WorkExperienceEntity> getAllWorkExperiencePersonEntity(PersonEntity idPerson) {
        return workExperienceRepository.findByIdPersonEntity(idPerson);
    }

    @Override
    public WorkExperienceEntity saveWorkExperience(WorkExperienceEntity workExperienceEntity) {
        WorkExperienceEntity workExperience = new WorkExperienceEntity();
        workExperience.setWork(workExperienceEntity.getWork());
        workExperience.setDescriptionWork(workExperienceEntity.getDescriptionWork());
        workExperience.setYear(workExperienceEntity.getYear());
        workExperience.setLogoBusinessUrl(workExperienceEntity.getLogoBusinessUrl());
        PersonEntity personEntity = personService.findPersonById(PERSON_ID);

        workExperience.setPersonEntity(personEntity);
        return workExperienceRepository.save(workExperience);
    }

    @Override
    public void saveWorkUpdate(WorkExperienceEntity workExperienceEntity) {
         workExperienceRepository.save(workExperienceEntity);
    }

    @Override
    public WorkExperienceEntity updateWork(String id, WorkExperienceDTO workExperienceDTO) {
        Optional<WorkExperienceEntity> experience = workExperienceRepository.findById(id);
        if(experience.isPresent()){
            WorkExperienceEntity experienceEntity = experience.get();
            PersonEntity personEntity= personService.findPersonById(PERSON_ID);
            experienceEntity.setPersonEntity(personEntity);
            System.out.println("Estoy en el método updateWork");
            return workExperienceRepository.save(experienceEntity);
        }
        System.out.println("Holaa");
            throw new ParamBadRequest("No existe el id solicitado");
    }


    @Override
    public void deleteWorkExperience(String id) {
        workExperienceRepository.deleteById(id);
    }

    @Override
    public WorkExperienceEntity findWorkExperienceById(String id) {
        return workExperienceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return workExperienceRepository.existsById(id);
    }

    @Override
    public Optional<WorkExperienceEntity> getOne(String id) {
        return workExperienceRepository.findById(id);
    }
}
