package br.com.ntendencia.dto;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.enums.EStatus;

import java.time.LocalDate;

public class ItemEmprestadoDTO {

    private String id;
    private String name;
    private String idMutuante;
    // Criar DTO
    private Integer idItemEmprestado;
    private LocalDate dataEmprestimo;
    private long qtdDiasDeDevolucao;
    private EStatus eStatus;

    public ItemEmprestadoDTO(){

    }

    public ItemEmprestadoDTO(ItemEmprestado obj) {
        this.id =obj.getId();
        this.name = obj.getName();
        this.idMutuante = obj.getIdMutuante();
        this.idItemEmprestado = obj.getIdItemEmprestado();
        this.dataEmprestimo=obj.getDataEmprestimo();
        this.qtdDiasDeDevolucao=obj.getQtdDiasDeDevolucao();
        this.eStatus=obj.geteStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdMutuante() {
        return idMutuante;
    }

    public void setIdMutuante(String idMutuante) {
        this.idMutuante = idMutuante;
    }

    public Integer getIdItemEmprestado() {
        return idItemEmprestado;
    }

    public void setIdItemEmprestado(Integer idItemEmprestado) {
        this.idItemEmprestado = idItemEmprestado;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public long getQtdDiasDeDevolucao() {
        return qtdDiasDeDevolucao;
    }

    public void setQtdDiasDeDevolucao(long qtdDiasDeDevolucao) {
        this.qtdDiasDeDevolucao = qtdDiasDeDevolucao;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }
}
