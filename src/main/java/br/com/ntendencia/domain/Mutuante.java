package br.com.ntendencia.domain;

import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;



@RedisHash(value="c_mutuante")
public class Mutuante extends User{

	private Integer idReferencial;

	private List<ItemEmprestado> itemsEmprestados = new ArrayList<>();

	public Mutuante(){}

	public Mutuante(String id, String name, String email, Integer idReferencial) {
		super(id, name, email);
		this.idReferencial = idReferencial;
	}

	public Integer getIdReferencial() {
		return idReferencial;
	}

	public void setIdReferencial(Integer idReferencial) {
		this.idReferencial = idReferencial;
	}

	public List<ItemEmprestado> getItemsEmprestados() {
		return itemsEmprestados;
	}

}
