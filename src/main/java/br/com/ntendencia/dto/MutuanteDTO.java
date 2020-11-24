package br.com.ntendencia.dto;

import br.com.ntendencia.domain.Mutuante;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MutuanteDTO {

    //Validações porém a messagem não é passada
    private String id;
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(min = 3, message = "Minímo de 3 caracteres")
    private String name;
    @Email
    @NotEmpty(message = "O campo não pode estar vazio")
    private String email;
    private Integer idMutuante;

    public MutuanteDTO() {

    }

    public MutuanteDTO(Mutuante obj) {
        this.id = obj.getIdUsuario();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.idMutuante = obj.getIdMutuante();
    }

    public MutuanteDTO(Mutuante obj, Integer idMutuante) {
        this.id = obj.getIdUsuario();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.idMutuante = idMutuante;
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