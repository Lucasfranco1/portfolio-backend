package com.portfolio.lucasfranco.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;
@Data
public class EducationDTO {

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

    private String logoEducationUrl;


    public EducationDTO(String education, Integer year, String descriptionEducation, String logoEducationUrl) {
        this.education = education;
        this.year = year;
        this.descriptionEducation = descriptionEducation;
        this.logoEducationUrl = logoEducationUrl;
    }
}
