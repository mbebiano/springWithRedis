package br.com.ntendencia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash(value="c_mutuario")
public class Mutuario extends User {


	private Integer idReferencial;
	private String senha;

//	private List<ItemEmprestado> itemsEmprestados = new ArrayList<>();

	public Mutuario() {

	}

	public Mutuario(String id, String name, String email, String senha,Integer idReferencial) {
		super(id, name, email);
		this.senha = senha;
		this.idReferencial = idReferencial;
	}

	public Integer getIdReferencial() {
		return idReferencial;
	}

	public void setIdReferencial(Integer idReferencial) {
		this.idReferencial = idReferencial;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public List<ItemEmprestado> getItemsEmprestados() {
//		return itemsEmprestados;
//	}

}
