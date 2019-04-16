package com.fatlab.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LaboratorioDTO{
    
    @Type(type = "IntegerTypes")
    @NotNull()
    private String nome;

    
}