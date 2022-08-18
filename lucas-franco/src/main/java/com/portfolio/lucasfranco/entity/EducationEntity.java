package com.portfolio.lucasfranco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@Entity
@Table(name = EDUCATION)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationEntity {
    @Id
    @GeneratedValue(generator = UUID)
    @GenericGenerator(name = UUID, strategy = UUID2)
    private String id;

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 50, message = MESSAGE_SIZE_LIMIT)
    private String education;

    @NotNull(message = MESSAGE_NOT_NULL)
    private Integer year;

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 250, message = MESSAGE_SIZE_LIMIT)
    private String descriptionEducation;

    @ManyToOne
    private PersonEntity personEntity;

    private String logoEducationUrl;
}
