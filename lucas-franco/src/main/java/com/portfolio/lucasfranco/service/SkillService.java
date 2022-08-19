package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.SkillEntity;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    public List<SkillEntity> getAllSkills();

    //List skill for personEntity
    public List<SkillEntity> getAllSkillPersonEntity(PersonEntity idPerson);


    public SkillEntity saveSkill(SkillEntity skillEntity);

    public void saveSkillUpdate(SkillEntity skillEntity);

    public Optional<SkillEntity> getOneSkill(String id);

    public boolean existsById(String id);
    //delete work

    public void deleteSkill(String id);

    //find work
    public SkillEntity findSkillById(String id);

}
