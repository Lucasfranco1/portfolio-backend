package com.portfolio.lucasfranco.service;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.ProjectEntity;
import com.portfolio.lucasfranco.exceptions.MyException;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public List<ProjectEntity> getAllProjects();

    public List<ProjectEntity> getAllProjectsPersonEntity(PersonEntity idPerson) throws MyException;


    public ProjectEntity saveProject(ProjectEntity projectEntity);

    public void saveProjectUpdate(ProjectEntity projectEntity);


    public void deleteProject(String id);


    public ProjectEntity findProjectById(String id);

    public boolean existsById(String id);

    public Optional<ProjectEntity> getOneProject(String id);
}
