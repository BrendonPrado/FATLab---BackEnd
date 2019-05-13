package com.fatlab.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatlab.domain.enums.Funcao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String senha;




	@JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ACESSO_USUARIOS")
    private Set<Integer> funcoes = new HashSet<>(  );

	

	public Usuario(Integer id, String nome, String email, String senha, boolean admin,Funcao func) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		addFuncao(func);
		if(admin==true){
			addFuncao(Funcao.ADMIN);
		}
	}

	public Set<Funcao> getFuncao() {
		return funcoes.stream().map( x -> Funcao.toEnum(x) ).collect( Collectors.toSet() );}

	public void addFuncao(Funcao func){
		this.funcoes.add(func.getCod());
	}



}
