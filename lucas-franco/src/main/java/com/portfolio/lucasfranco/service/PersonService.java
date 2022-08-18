package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.PersonEntity;

import java.util.List;

public interface PersonService {

    //List Person
    public List<PersonEntity>getPerson();

    public void findPhotoProfilePerson(String id);

    //save Person
    public PersonEntity savePerson(PersonEntity personEntity);


    //delete Person
    public void deletePerson(String id);
    public boolean existsById(String id);

    public void savePersonUpdate(PersonEntity personEntity);

    //find Person
    public PersonEntity findPersonById(String id);

}
