package com.fatlab.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Materia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	
	
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	@JsonIgnore
	@ManyToMany(mappedBy="materias")
	private Set<Aluno> alunos;
	
	private String turma;
	
	@JsonIgnore
	@OneToMany(mappedBy="materia",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Reserva> reservas = new HashSet<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Materia(String nome, Professor professor, String turma) {
		super();
		this.nome = nome;
		this.professor = professor;
		this.turma = turma;
	}

	public void addReserva(Reserva reserva) {

		this.reservas.add(reserva);
	}
	
	public void addAlunos(Aluno aluno) {
		this.alunos.add(aluno);
	}

	

	
	
}
