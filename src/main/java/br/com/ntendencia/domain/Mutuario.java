package br.com.ntendencia.domain;


import br.com.ntendencia.dto.ItemEmprestadoDTO;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash(value="c_mutuario")
public class Mutuario extends Usuario {

	private Integer idMutuario;
	private String senha;

	public Mutuario() {

	}

	public Mutuario(String id, String name, String email, String senha,Integer idMutuario) {
		super(id, name, email);
		this.senha = senha;
		this.idMutuario = idMutuario;
	}

	public Integer getIdMutuario() {
		return idMutuario;
	}

	public void setIdMutuario(Integer idMutuario) {
		this.idMutuario = idMutuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
