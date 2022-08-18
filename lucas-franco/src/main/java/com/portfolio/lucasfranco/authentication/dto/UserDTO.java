package com.portfolio.lucasfranco.authentication.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String nameUser;

    @Email(message = "Must be a properly formatted email address.")
    @NotEmpty(message = "The field must not be empty.")
    private String email;

    @Size(min=8,message="Min 8 characters in password")
    @NotEmpty(message = "The field must not be empty.")
    private String password;

    private Set<String> roles= new HashSet<>();

}
