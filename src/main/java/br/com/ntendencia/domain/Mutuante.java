package br.com.ntendencia.domain;

import br.com.ntendencia.dto.ItemEmprestadoDTO;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;



@RedisHash(value="c_mutuante")
public class Mutuante extends User{

	private Integer idReferencial;

	private List<ItemEmprestadoDTO> itemEmprestadoDTO = new ArrayList<>();

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

	public List<ItemEmprestadoDTO> getItemEmprestadoDTO() {
		return itemEmprestadoDTO;
	}

}
