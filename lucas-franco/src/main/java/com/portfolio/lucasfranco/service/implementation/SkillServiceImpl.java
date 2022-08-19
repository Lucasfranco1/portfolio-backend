package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.SkillEntity;
import com.portfolio.lucasfranco.repository.SkillRepository;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.PERSON_ID;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private PersonService personService;


    @Override
    public List<SkillEntity> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public List<SkillEntity> getAllSkillPersonEntity(PersonEntity idPerson) {
        return skillRepository.findByIdPersonEntity(idPerson);
    }

    @Override
    public SkillEntity saveSkill(SkillEntity skillEntity) {
        SkillEntity skill = new SkillEntity();
        skill.setNameSkill(skillEntity.getNameSkill());
        skill.setPercentSkill(skillEntity.getPercentSkill());
        skill.setImageSkill(skillEntity.getImageSkill());

        PersonEntity personEntity = personService.findPersonById(PERSON_ID);
        skill.setPerson(personEntity);

        return skillRepository.save(skill);
    }

    @Override
    public void saveSkillUpdate(SkillEntity skillEntity) {
        skillRepository.save(skillEntity);
    }

    @Override
    public Optional<SkillEntity> getOneSkill(String id) {
        return skillRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return skillRepository.existsById(id);
    }

    @Override
    public void deleteSkill(String id) {
        skillRepository.deleteById(id);
    }

    @Override
    public SkillEntity findSkillById(String id) {
        return skillRepository.findById(id).orElse(null);
    }
}
