package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.PersonEntity;

import java.util.List;

public interface PersonService {

    public List<PersonEntity>getPerson();

    public void findPhotoProfilePerson(String id);

    public PersonEntity savePerson(PersonEntity personEntity);

    public void deletePerson(String id);

    public boolean existsById(String id);

    public void savePersonUpdate(PersonEntity personEntity);


    public PersonEntity findPersonById(String id);

}
