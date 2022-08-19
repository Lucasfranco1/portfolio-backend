package com.portfolio.lucasfranco.service.implementation;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.ProjectEntity;
import com.portfolio.lucasfranco.exceptions.MyException;
import com.portfolio.lucasfranco.repository.ProjectRepository;
import com.portfolio.lucasfranco.service.PersonService;
import com.portfolio.lucasfranco.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.PERSON_ID;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonService personService;

    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectEntity> getAllProjectsPersonEntity(PersonEntity idPerson) throws MyException {
        if(idPerson!=null){
            return projectRepository.findByIdPersonEntity(idPerson);
        }else {
            throw new MyException("Id not exist");
        }

    }

    @Override
    public ProjectEntity saveProject(ProjectEntity projectEntity) {
        ProjectEntity project = new ProjectEntity();
        project.setNameProject(projectEntity.getNameProject());
        project.setDescriptionProject(projectEntity.getDescriptionProject());
        project.setUrlProject(projectEntity.getUrlProject());
        project.setImageProject(projectEntity.getImageProject());

        PersonEntity personEntity = personService.findPersonById(PERSON_ID);
        project.setPerson(personEntity);

        return projectRepository.save(project);
    }

    @Override
    public void saveProjectUpdate(ProjectEntity projectEntity) {
        projectRepository.save(projectEntity);
    }

    @Override
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectEntity findProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return projectRepository.existsById(id);
    }

    @Override
    public Optional<ProjectEntity> getOneProject(String id) {
        return projectRepository.findById(id);
    }
}
