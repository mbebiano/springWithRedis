package br.com.ntendencia.dto;

import br.com.ntendencia.domain.ContratoEmprestimo;

import java.util.ArrayList;
import java.util.List;

public class ContratoEmprestimoDTO {

    private String id;

    private String idMutuario;

    private List<String> listaIdsItens = new ArrayList<>();

    public ContratoEmprestimoDTO(){}

    public ContratoEmprestimoDTO(ContratoEmprestimo obj) {
        this.id =obj.getId();
        this.idMutuario = obj.getIdMutuario();
        this.listaIdsItens= obj.getListaIdsItens();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMutuario() {
        return idMutuario;
    }

    public void setIdMutuario(String idMutuario) {
        this.idMutuario = idMutuario;
    }

    public List<String> getListaIdsItens() {
        return listaIdsItens;
    }

    public void setListaIdsItens(List<String> listaIdsItens) {
        this.listaIdsItens = listaIdsItens;
    }
}
