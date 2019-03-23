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


	@OneToOne()
	@JoinColumn(name = "ra_id")
	private RA_TIPO ra;

	@JsonIgnore
	@OneToMany(mappedBy="professor")
	private Set<Materia> materias = new HashSet<>() ;

	public Professor(Integer id,String nome, String email, String senha, RA_TIPO rA) {
		super(id,nome, email, senha);
		ra = rA;
	}

	public Professor(Integer id,String nome, String email, String senha) {
		super(id,nome, email, senha);
	}


	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	
}
