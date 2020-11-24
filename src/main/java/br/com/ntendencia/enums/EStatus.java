package br.com.ntendencia.enums;

public enum EStatus {
    DISPONIVEL("Disponível"),
    EMPRESTADO("Emprestado"),
    INDISPONIVEL("Indisponível");

    private final String nome;

    EStatus(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}