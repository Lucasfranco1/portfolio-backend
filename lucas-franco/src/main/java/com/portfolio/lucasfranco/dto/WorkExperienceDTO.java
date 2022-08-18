package com.portfolio.lucasfranco.dto;

import com.portfolio.lucasfranco.entity.PersonEntity;
import lombok.Data;
import lombok.Getter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;
import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.MESSAGE_SIZE_LIMIT;

@Data
public class WorkExperienceDTO {

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 50, message = MESSAGE_SIZE_LIMIT)
    private String work;

    @NotNull(message = MESSAGE_NOT_NULL)
    private Integer year;

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @Size(min = 2, max = 250, message = MESSAGE_SIZE_LIMIT)
    private String descriptionWork;

    private String logoBusinessUrl;

    public WorkExperienceDTO(String work, Integer year, String descriptionWork, String logoBusinessUrl) {
        this.work = work;
        this.year = year;
        this.descriptionWork = descriptionWork;
        this.logoBusinessUrl = logoBusinessUrl;
    }
}
