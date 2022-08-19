package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.Message;
import com.portfolio.lucasfranco.dto.SkillDTO;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.SkillEntity;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.SkillService;
import com.portfolio.lucasfranco.service.implementation.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@RestController
@RequestMapping(PATH_SKILL)
@CrossOrigin(origins = CROSS_ORIGIN)
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private PersonService personService;


    @GetMapping(PATH_GET_ALL)
    public List<SkillEntity>getAllSkills(){
        return skillService.getAllSkills();
    }

    @PreAuthorize(ADMIN)
    @PostMapping(CREATE)
    public ResponseEntity<SkillEntity> saveSkill(@Validated @RequestBody SkillEntity skillEntity){
        SkillEntity skill = skillService.saveSkill(skillEntity);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @GetMapping(DETAIL_ID)
    public ResponseEntity<SkillEntity> getByIdSkill(@PathVariable(ID) String id){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        SkillEntity skill = skillService.getOneSkill(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE)
    public ResponseEntity<SkillEntity> updateSkill(@PathVariable String id, @RequestBody SkillDTO skillDTO){

        SkillEntity skillEntity= skillService.findSkillById(id);
        skillEntity.setNameSkill(skillDTO.getNameSkill());
        skillEntity.setPercentSkill(skillDTO.getPercentSkill());
        skillEntity.setImageSkill(skillDTO.getImageSkill());

        skillService.saveSkillUpdate(skillEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize(ADMIN)
    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteSkillById(@PathVariable String id){
        skillService.deleteSkill(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<SkillEntity> getAllSkillsByPerson(){
        PersonEntity person= personService.findPersonById(PERSON_ID);
        return skillService.getAllSkillPersonEntity(person);
    }
}
