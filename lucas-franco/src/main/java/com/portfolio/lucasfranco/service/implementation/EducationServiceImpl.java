package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.entity.EducationEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.repository.EducationRepository;
import com.portfolio.lucasfranco.service.EducationService;
import com.portfolio.lucasfranco.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.PERSON_ID;

@Service
@Transactional
public class EducationServiceImpl implements EducationService {

    @Autowired
    private PersonService personService;
    @Autowired
    private EducationRepository educationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EducationEntity> getAllEducation() {
        List<EducationEntity>entities=educationRepository.findAll();
        return entities;
    }

    @Override
    public List<EducationEntity> getAllEducationPersonEntity(PersonEntity idPerson) {
        return educationRepository.findByIdPersonEntity(idPerson);
    }

    @Override
    public EducationEntity saveEducation(EducationEntity educationEntity) {
        EducationEntity education = new EducationEntity();
        education.setEducation(educationEntity.getEducation());
        education.setDescriptionEducation(educationEntity.getDescriptionEducation());
        education.setYear(educationEntity.getYear());
        education.setLogoEducationUrl(educationEntity.getLogoEducationUrl());

        PersonEntity personEntity = personService.findPersonById(PERSON_ID);
        education.setPersonEntity(personEntity);

        return educationRepository.save(education);
    }

    @Override
    public void saveEducationUpdate(EducationEntity education) {
        educationRepository.save(education);
    }

    @Override
    public Optional<EducationEntity> getOne(String id) {
        return educationRepository.findById(id);
    }

    @Override
    public void deleteEducation(String id) {
        educationRepository.deleteById(id);
    }

    @Override
    public EducationEntity findEducationById(String id) {
        return educationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return educationRepository.existsById(id);
    }
}
