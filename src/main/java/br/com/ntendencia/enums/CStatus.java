package br.com.ntendencia.enums;

public enum CStatus {
    CONTRATOEMDIA("ContratoEmDia"),
    CONTRATOEMATRASO("ContratoEmAtraso"),
    CONTRATOFINALIZADO("ContratoFinalizado");

    private final String nome;

    CStatus(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}