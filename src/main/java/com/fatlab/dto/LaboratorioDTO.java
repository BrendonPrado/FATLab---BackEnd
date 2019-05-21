package com.fatlab.dto;


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
    private Integer numero;

    @Type(type = "IntegerTypes")
    private Integer capacidade;

    
}