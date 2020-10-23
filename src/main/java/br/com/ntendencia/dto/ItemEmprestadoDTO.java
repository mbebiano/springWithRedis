package br.com.ntendencia.dto;

import br.com.ntendencia.domain.ItemEmprestado;

public class ItemEmprestadoDTO {

    private String id;
    private String name;

    public ItemEmprestadoDTO(){}

    public ItemEmprestadoDTO(ItemEmprestado obj) {
        this.id =obj.getId();
        this.name = obj.getName();
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
}
