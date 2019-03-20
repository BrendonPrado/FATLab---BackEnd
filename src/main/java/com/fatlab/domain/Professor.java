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

	@OneToOne
	@JoinColumn(name = "ra_id")
	@Column(unique = true)
	private RA_TIPO RA;
	
	@JsonIgnore
	@OneToMany(mappedBy="professor")
	private Set<Materia> materias = new HashSet<>() ;

	public Professor(String nome, String email, String senha, RA_TIPO rA) {
		super(nome, email, senha);
		RA = rA;
	}
	
	
	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	
}
