package com.fatlab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonTypeName("Professor")
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;


	private String matricula;

	@JsonIgnore
	@OneToMany(mappedBy="professor")
	private Set<Materia> materias = new HashSet<>() ;

	public Professor(Integer id, String nome, String email, String senha, boolean admin, String matricula) {
		super(id, nome, email, senha, admin);
		this.matricula = matricula;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	
}
