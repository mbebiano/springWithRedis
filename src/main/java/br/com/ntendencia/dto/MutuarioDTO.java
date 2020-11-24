package br.com.ntendencia.dto;

import br.com.ntendencia.domain.Mutuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MutuarioDTO {

    @NotEmpty(message = "{card.id.empty}")
    @Size(min = 3, message = "Minímo de 3 caracteres")
    private String name;
    @Email
    @NotEmpty(message = "O campo não pode estar vazio")
    private String email;
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(min = 8, message = "Minímo de 8 caracteres")
    private String senha;
    private Integer idMutuario;

    public MutuarioDTO() {
    }

    public MutuarioDTO(MutuarioDTO objDTO) {
        this.name = objDTO.getName();
        this.email = objDTO.getEmail();
        this.idMutuario = objDTO.getIdMutuario();
        this.senha = objDTO.getSenha();
    }

    public MutuarioDTO(Mutuario obj) {
        this.idMutuario = obj.getIdMutuario();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }

    public MutuarioDTO(Mutuario obj, Integer idMutuario) {
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}