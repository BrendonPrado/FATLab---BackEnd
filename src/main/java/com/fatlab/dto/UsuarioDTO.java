package com.fatlab.dto;

import java.io.Serializable;

import com.fatlab.service.validator.UsuarioDTOValido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@UsuarioDTOValido
public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	private String matricula;
	private String tipo;
	private boolean admin;
	
	public UsuarioDTO(String matricula, String tipo, boolean admin) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.admin = admin;
	}

	public boolean dtoIsAluno(){
		return this.getTipo().equals("Aluno");
	}

	public boolean dtoIsProfessor(){
		return this.getTipo().equals("Professor");
	}
}
