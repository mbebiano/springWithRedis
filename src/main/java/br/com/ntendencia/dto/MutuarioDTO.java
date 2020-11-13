package br.com.ntendencia.dto;

import br.com.ntendencia.domain.Mutuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class MutuarioDTO {

	private String id;
	@NotBlank
	private String name;
	@NotEmpty
	private String email;

	private Integer idMutuario;

	public MutuarioDTO(){}

	public MutuarioDTO(Mutuario obj) {
		this.id = obj.getIdUsuario();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}
	public MutuarioDTO(Mutuario obj, Integer idMutuario) {
		this.id = obj.getIdUsuario();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.idMutuario = idMutuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdMutuario() {
		return idMutuario;
	}

	public void setIdMutuario(Integer idMutuario) {
		this.idMutuario = idMutuario;
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
