package com.portfolio.lucasfranco.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private String token;

    public JwtDTO(){

    }
    public JwtDTO(String token) {
        this.token = token;
    }
}
