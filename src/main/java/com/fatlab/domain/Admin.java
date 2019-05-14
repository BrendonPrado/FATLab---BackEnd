package com.fatlab.domain;



import javax.persistence.Entity;

import com.fatlab.domain.enums.Funcao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admin extends Usuario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Admin(Integer id, String nome, String email, String senha) {
		super(id, nome, email, senha, true,Funcao.Admin);
	}



}
