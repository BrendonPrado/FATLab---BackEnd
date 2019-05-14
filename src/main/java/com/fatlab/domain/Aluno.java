package com.fatlab.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="Alunos_Materias",
	joinColumns=@JoinColumn(name="aluno_id"),
	inverseJoinColumns=@JoinColumn(name="materia_id"))
	private List<Materia> materias = new ArrayList<>();


	public Aluno(Integer id, String nome, String email, String senha, boolean admin, String RA) {
		super(id, nome, email, senha, admin,
		Funcao.Aluno);
		this.ra = RA;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}




	
}
