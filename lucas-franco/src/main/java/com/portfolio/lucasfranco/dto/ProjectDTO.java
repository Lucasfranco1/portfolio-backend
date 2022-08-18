package com.portfolio.lucasfranco.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.MESSAGE_NOT_EMPTY;

@Data
public class ProjectDTO {
    @NotNull
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String nameProject;

    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String descriptionProject;

    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String urlProject;

    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String imageProject;
}
