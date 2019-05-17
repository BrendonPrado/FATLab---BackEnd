package com.fatlab.domain.enums;

public enum Funcao {
	Admin(1, "ROLE_ADMIN"), Professor(2, "ROLE_PROFESSOR"), Aluno(3, "ROLE_ALUNO");

	private int tipo;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public int getCod() {
		return this.tipo;
	}

	private Funcao(int cod, String descricao) {
		this.tipo = cod;
		this.descricao = descricao;
	}

	public static Funcao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Funcao x : Funcao.values()) {
			if (cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: " + cod + " não achado ");
	}

	public static Funcao toEnum(String tipo) {
		if (tipo == null) {
			return null;
		}

		for (Funcao x : Funcao.values()) {
			if (x.toString().equals(tipo)) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: " + tipo + " não achado ");
	}

}