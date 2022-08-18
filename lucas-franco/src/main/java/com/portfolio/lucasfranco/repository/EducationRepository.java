package com.portfolio.lucasfranco.repository;

import com.portfolio.lucasfranco.entity.EducationEntity;
import com.portfolio.lucasfranco.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, String> {
    /**
     *
     * @param idPerson
     * @return All work education the one person
     */
    @Query(value = "SELECT w FROM EducationEntity w WHERE w.personEntity= :personEntity ORDER BY w.year DESC")
    public List<EducationEntity> findByIdPersonEntity(@Param("personEntity") PersonEntity idPerson);
}
