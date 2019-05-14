package com.fatlab.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatlab.domain.enums.Funcao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;


	@Column(unique = true)
	private String matricula;

	@JsonIgnore
	@OneToMany(mappedBy="professor",fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	private List<Materia> materias = new ArrayList<>() ;

	public Professor(Integer id, String nome, String email, String senha, boolean admin, String matricula) {
		super(id, nome, email, senha, admin,Funcao.Professor);
		this.matricula = matricula;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	
}
