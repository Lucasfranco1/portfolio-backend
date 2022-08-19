package com.portfolio.lucasfranco.controller;

import com.portfolio.lucasfranco.dto.Message;
import com.portfolio.lucasfranco.dto.ProjectDTO;
import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.ProjectEntity;
import com.portfolio.lucasfranco.exceptions.MyException;
import com.portfolio.lucasfranco.service.ImageService;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.ProjectService;
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
@RequestMapping("/projects")
@CrossOrigin(origins = CROSS_ORIGIN)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PersonService personService;

    @GetMapping(PATH_GET_ALL)
    public List<ProjectEntity> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PreAuthorize(ADMIN)
    @PostMapping(CREATE)
    public ResponseEntity<ProjectEntity> saveProject(@Validated @RequestBody ProjectEntity projectEntity){
        ProjectEntity project = projectService.saveProject(projectEntity);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping(DETAIL_ID)
    public ResponseEntity<ProjectEntity> getByIdProject(@PathVariable(ID) String id){
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message(ERROR_BY_ID), HttpStatus.NOT_FOUND);
        ProjectEntity project = projectService.getOneProject(id).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @PreAuthorize(ADMIN)
    @PutMapping(UPDATE)
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable String id, @RequestBody ProjectDTO projectDTO){
        ProjectEntity projectEntity= projectService.findProjectById(id);
        projectEntity.setNameProject(projectDTO.getNameProject());
        projectEntity.setDescriptionProject(projectDTO.getDescriptionProject());
        projectEntity.setUrlProject(projectDTO.getUrlProject());
        projectEntity.setImageProject(projectDTO.getImageProject());

        projectService.saveProjectUpdate(projectEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize(ADMIN)
    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteProjectById(@PathVariable String id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<ProjectEntity> getAllProjectsByPerson() throws MyException {
        PersonEntity person= personService.findPersonById(PERSON_ID);
        return projectService.getAllProjectsPersonEntity(person);
    }
}
