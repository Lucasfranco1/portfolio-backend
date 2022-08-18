package com.portfolio.lucasfranco.repository;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    /**
     *
     * @param idPerson
     * @return All projects the one person
     */
    @Query(value = "SELECT p FROM ProjectEntity p WHERE p.person= :person")
    public List<ProjectEntity> findByIdPersonEntity(@Param("person") PersonEntity idPerson);
}
