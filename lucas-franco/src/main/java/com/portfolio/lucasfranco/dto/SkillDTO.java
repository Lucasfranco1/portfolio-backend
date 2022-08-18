package com.portfolio.lucasfranco.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.MESSAGE_NOT_EMPTY;
import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.MESSAGE_NOT_NULL;
@Data
public class SkillDTO {
    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    private String nameSkill;
    @NotNull
    private Integer percentSkill;

    private String imageSkill;

}
