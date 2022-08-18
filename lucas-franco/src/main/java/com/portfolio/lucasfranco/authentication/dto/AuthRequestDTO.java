package com.portfolio.lucasfranco.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthRequestDTO {
    @NotBlank
    private String nameUser;
    @NotBlank
    @Size(min = 8, max = 20, message = "Debe tener entre 8 y 20 caracteres")
    private String password;
}
