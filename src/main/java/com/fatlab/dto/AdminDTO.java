package com.fatlab.dto;

import javax.validation.constraints.Email;

import com.fatlab.service.validator.EmailValid;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AdminDTO
 */

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO {

    @EmailValid
    @Email(message = "Deve ser um email válido")
    private String email;
    

    @Length(min = 3, max = 20)
    private String senha;
}