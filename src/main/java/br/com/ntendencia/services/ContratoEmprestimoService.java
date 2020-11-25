package br.com.ntendencia.services;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;

import java.util.List;

public interface ContratoEmprestimoService {

    /**
     * Salva um {@link ContratoEmprestimo}
     *
     * @param contratoEmprestimoDTO Objeto {@link ContratoEmprestimoDTO}
     * @return {@link ContratoEmprestimo salvo}
     */
    ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimoDTO contratoEmprestimoDTO);

    /**
     * Procura e deleta, se existir, um objeto {@link ContratoEmprestimo}
     *
     * @param id Id de um objeto {@link ContratoEmprestimoDTO}
     */
    void deleteContratoEmprestimo(String id);

    /**
     * Lista todos objetos {@link ContratoEmprestimo}
     *
     * @return Lista de {@link ContratoEmprestimo}
     */
    List<ContratoEmprestimo> listarTodoscontratosEmprestimo();

    /**
     * Lista todos objetos {@link ContratoEmprestimoDTO}
     *
     * @return Lista de {@link ContratoEmprestimoDTO}
     */
    List<ContratoEmprestimoDTO> listarTodoscontratosEmprestimoDTO();

    /**
     * Gera um id de forma incremental com base no ultimo objeto salvo
     *
     * @return id gerado de forma incremental
     */
    Integer gerarId();
}