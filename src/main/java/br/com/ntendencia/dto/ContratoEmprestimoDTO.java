package br.com.ntendencia.dto;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;

import java.util.ArrayList;
import java.util.List;

public class ContratoEmprestimoDTO {

    private String id;

    private String idMutuario;

    private List<ItemEmprestado> itensEmprestados = new ArrayList<>();

    public ContratoEmprestimoDTO(){

    }
    public ContratoEmprestimoDTO(ContratoEmprestimo obj) {
        this.id =obj.getId();
        this.idMutuario = obj.getIdMutuario();
        this.itensEmprestados= obj.getItensEmprestados();
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

    public List<ItemEmprestado> getItensEmprestados() {
        return itensEmprestados;
    }

    public void setItensEmprestados(List<ItemEmprestado> itensEmprestados) {
        this.itensEmprestados = itensEmprestados;
    }
}
