package com.fatlab.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fatlab.domain.enums.Funcao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String ra;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="Alunos_Materias",
	joinColumns=@JoinColumn(name="aluno_id"),
	inverseJoinColumns=@JoinColumn(name="materia_id"))
	private List<Materia> materias = new ArrayList<>();

	@OneToOne
	private Usuario usuario;

	public Aluno(Integer id_usuario, String nome, String email, String senha, boolean admin, String RA) {
		this.usuario = new Usuario(id_usuario, nome, email, senha, admin,Funcao.Aluno);
			this.ra = RA;
	}

	public void addMateria(Materia materia){
		this.materias.add(materia);
	}

	public Aluno(Usuario usuario, String ra) {
		this.usuario = usuario;
		this.ra = ra;
	}




	
}
