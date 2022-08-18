package com.portfolio.lucasfranco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;


@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(generator = UUID)
    @GenericGenerator(name = UUID, strategy = UUID2)
    private String id;

    @NotNull
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String nameProject;

    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String descriptionProject;

    private String urlProject;

    private String imageProject;

    @ManyToOne
    private PersonEntity person;

}
