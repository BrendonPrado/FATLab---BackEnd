package com.fatlab.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fatlab.service.validator.UsuarioNewDTOValido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@UsuarioNewDTOValido
public class UsuarioNewDTO {
    @NotEmpty(message = "O nome não pode estar vazio")
    private String nome;

    @Email(message = "Por favor insira um email válido")
    private String email;
    
    @NotBlank(message = "A senha não pode estar vazia")
    @NotEmpty(message = "A senha não pode estar vazia")
    @Length(min = 3,max = 20)
    private String senha;
    
    @NotEmpty(message = "A matricula não pode estar vazia")
    private String matricula;

    public UsuarioNewDTO(String nome, String email, String senha, String matricula) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
    }
}
