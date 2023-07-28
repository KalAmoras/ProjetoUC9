package models;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}