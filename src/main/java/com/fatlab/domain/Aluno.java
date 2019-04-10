package com.fatlab.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatlab.domain.enums.Funcao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Aluno extends Usuario {
	private static final long serialVersionUID = 1L;


	@Column(unique = true)
	private String ra;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="Alunos_Materias",
	joinColumns=@JoinColumn(name="aluno_id"),
	inverseJoinColumns=@JoinColumn(name="materia_id"))
	private Set<Materia> materias= new HashSet<>();


	public Aluno(Integer id, String nome, String email, String senha, boolean admin, String RA) {
		super(id, nome, email, senha, admin,
		Funcao.ALUNO);
		this.ra = RA;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}




	
}
