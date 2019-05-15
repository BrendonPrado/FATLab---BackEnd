package com.fatlab.domain;

import java.io.Serializable;
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
public class Professor implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String matricula;

	@JsonIgnore
	@OneToMany(mappedBy="professor",fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	private List<Materia> materias = new ArrayList<>() ;

	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	public Professor(Integer id, String nome, String email, String senha, boolean admin, String matricula) {
		this.usuario = new Usuario(id, nome, email, senha, admin,Funcao.Professor);
		this.matricula = matricula;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	public Professor(Usuario usuario, String matricula) {
		this.usuario = usuario;
		this.matricula = matricula;
	}

	
}
