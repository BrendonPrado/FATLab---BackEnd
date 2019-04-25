package com.fatlab.dto;

import javax.validation.constraints.NotBlank;

import com.fatlab.service.validator.LabDTOValido;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@LabDTOValido
public class LaboratorioDTO{
    
    @Type(type = "IntegerTypes")
    @NotBlank()
    private String numero;

    @Type(type = "IntegerTypes")
    private String capacidade;

    
}