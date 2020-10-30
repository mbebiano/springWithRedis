package br.com.ntendencia.domain;

import org.springframework.data.redis.core.RedisHash;



@RedisHash(value="c_mutuante")
public class Mutuante extends Usuario{

	private Integer idMutuante;

	public Mutuante(){}

	public Mutuante(String id , String email, String name, Integer idMutuante) {
		super(id, name, email);
		this.idMutuante = idMutuante;
	}

	public Integer getIdMutuante() {
		return idMutuante;
	}

	public void setIdMutuante(Integer idMutuante) {
		this.idMutuante = idMutuante;
	}
	
}
