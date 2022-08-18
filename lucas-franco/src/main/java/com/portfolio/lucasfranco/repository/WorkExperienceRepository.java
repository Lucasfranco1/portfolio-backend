package com.portfolio.lucasfranco.repository;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.WorkExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceEntity, String> {

    /**
     *
     * @param idPerson
     * @return All work experience the one person
     */
    @Query(value = "SELECT w FROM WorkExperienceEntity w WHERE w.personEntity= :personEntity ORDER BY w.year DESC" )
    public List<WorkExperienceEntity>findByIdPersonEntity(@Param("personEntity") PersonEntity idPerson);
}
