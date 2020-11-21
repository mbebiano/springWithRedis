package br.com.ntendencia.dto;

import br.com.ntendencia.domain.Mutuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MutuarioDTO {

	private String id;
	@NotEmpty(message = "{card.id.empty}")
	@Size(min = 3, message = "Minímo de 3 caracteres")
	private String name;
	@Email
	@NotEmpty (message = "O campo não pode estar vazio")
	private String email;

	private Integer idMutuario;

	public MutuarioDTO(){}

	public MutuarioDTO(MutuarioDTO objDTO){
		this.name = objDTO.getName();
		this.email = objDTO.getEmail();
		this.idMutuario= objDTO.getIdMutuario();
	}
	public MutuarioDTO(Mutuario obj) {
		this.idMutuario = obj.getIdMutuario();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}
	public MutuarioDTO(Mutuario obj, Integer idMutuario) {
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.idMutuario = idMutuario;
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
