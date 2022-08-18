package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.repository.PersonRepository;
import com.portfolio.lucasfranco.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PersonEntity> getPerson() {
        List<PersonEntity>persons=personRepository.findAll();
        return persons;
    }

    @Override
    public void findPhotoProfilePerson(String id) {
        PersonEntity person= new PersonEntity();
        Optional<PersonEntity> personEntity= personRepository.findById(id);
        if(personEntity.isPresent()){
            person=personEntity.get();

        }
    }

    @Override
    @Transactional
    public PersonEntity savePerson(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    @Override
    @Transactional
    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return personRepository.existsById(id);
    }

    @Override
    public void savePersonUpdate(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }

    @Override
    public PersonEntity findPersonById(String id) {
        return personRepository.findById(id).orElse(null);
    }
}
