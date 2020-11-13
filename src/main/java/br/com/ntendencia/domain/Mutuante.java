package br.com.ntendencia.domain;

import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Mutuante)) return false;
		if (!super.equals(o)) return false;
		Mutuante mutuante = (Mutuante) o;
		return Objects.equals(getIdMutuante(), mutuante.getIdMutuante());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getIdMutuante());
	}
}
