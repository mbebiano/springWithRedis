package br.com.ntendencia.dto;

import br.com.ntendencia.domain.Mutuante;

public class MutuanteDTO {
	
	private String id;
	private String name;
	private String email;
	private Integer idMutuante;
	
	public MutuanteDTO() {
		
	}
	public MutuanteDTO(Mutuante obj) {
		this.id = obj.getIdUsuario();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public MutuanteDTO(Mutuante obj, Integer idMutuante) {
		this.id = obj.getIdUsuario();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.idMutuante=idMutuante;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdMutuante() {
		return idMutuante;
	}

	public void setIdMutuante(Integer idMutuante) {
		this.idMutuante = idMutuante;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
