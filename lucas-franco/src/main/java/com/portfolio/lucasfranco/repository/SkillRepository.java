package com.portfolio.lucasfranco.repository;

import com.portfolio.lucasfranco.entity.PersonEntity;
import com.portfolio.lucasfranco.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, String> {

    /**
     *
     * @param idPerson
     * @return All skills the one person
     */
    @Query(value = "SELECT s FROM SkillEntity s WHERE s.person= :person")
    public List<SkillEntity> findByIdPersonEntity(@Param("person") PersonEntity idPerson);
}
