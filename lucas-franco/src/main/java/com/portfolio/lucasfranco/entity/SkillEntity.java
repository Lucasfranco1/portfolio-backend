package com.portfolio.lucasfranco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@Entity
@Table(name = SKILL)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SkillEntity {
    @Id
    @GeneratedValue(generator = UUID)
    @GenericGenerator(name = UUID, strategy = UUID2)
    private String id;

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String nameSkill;

    private Integer percentSkill;

    private String imageSkill;

    @ManyToOne
    private PersonEntity person;
}
