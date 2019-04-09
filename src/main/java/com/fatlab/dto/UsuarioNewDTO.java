package com.fatlab.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioNewDTO {
    private String nome;
    private String email;
    private String senha;
    private String matricula;

    public UsuarioNewDTO(String nome, String email, String senha, String matricula) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
    }
}
