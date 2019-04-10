package com.fatlab.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LaboratorioDTO{
    
    @NotNull()
    private String nome;

    
}