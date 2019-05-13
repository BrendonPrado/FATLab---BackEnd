package com.fatlab.domain.enums;


public enum Funcao {
    ADMIN(1,"ROLE_ADMIN"),
    PROFESSOR(2,"ROLE_PROFESSOR"),
    ALUNO(3,"ROLE_ALUNO");


	private int cod;

    private String descricao;
    
    public String getDescricao() {
		return descricao;
	}
	public int getCod() {
		return this.cod;
	}




	private Funcao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public static Funcao toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Funcao x: Funcao.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: "+cod+" não achado ");
	}


}