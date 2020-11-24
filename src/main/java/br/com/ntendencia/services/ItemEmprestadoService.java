package br.com.ntendencia.services;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.enums.EStatus;

import java.util.List;

public interface ItemEmprestadoService {

    /**
     * Salva um objeto {@link ItemEmprestado}
     *
     * @param itemEmprestadoDTO DTO do objeto a ser salvo
     * @return um objeto salvo {@link ItemEmprestado}
     */
    ItemEmprestado salvarItemEmprestado(ItemEmprestadoDTO itemEmprestadoDTO);

    /***
     * Procura e deleta, se existir, um objeto {@link ItemEmprestado}
     * @param id Id de um objeto {@link ItemEmprestado}
     */
    void deletarItemEmprestado(String id);

    /**
     * Lista todos os objetos {@link ItemEmprestado}
     *
     * @return lista de {@link ItemEmprestado}
     */
    List<ItemEmprestado> listarItensEmprestados();

    /***
     * Procura e retorna, se existir, um objeto {@link ItemEmprestado}
     * @param id Id de um objeto {@link ItemEmprestado}
     * @return objeto de {@link ItemEmprestado}
     */
    ItemEmprestado procurarItemEmprestado(String id);

    /***
     * Procura e retorna, se existir, um objeto {@link ItemEmprestadoDTO}
     * @param id Id de um objeto {@link ItemEmprestadoDTO}
     * @return objeto de {@link ItemEmprestadoDTO}
     */
    ItemEmprestadoDTO procurarItemEmprestadoDTO(String id);

    /**
     * Altera o status de um {@link ItemEmprestado}
     *
     * @param id     do objeto {@link ItemEmprestado}
     * @param status enum de {@link EStatus} que define o tipo de status
     */
    void alterarStatusItemEmprestado(String id, EStatus status);

    /***
     * Gera um id de forma incremental com base no ultimo objeto salvo
     * @return id gerado de forma incremental
     */
    Integer gerarId();

    /**
     * Define uma data de emprestimo do {@link ItemEmprestado}
     *
     * @param itemEmprestado objeto {@link ItemEmprestado} que terá a data de empréstimo alterada
     */
    void definirDataDeEmprestimo(ItemEmprestado itemEmprestado);

    /**
     * Atualiza dados do {@link ItemEmprestado}
     *
     * @param novoObj objeto de {@link ItemEmprestado} que possui dados atualizados
     * @param obj     objeto de {@link ItemEmprestado} que possui dados a ser atualizado
     */
    void atualizarDadoItemEmprestado(ItemEmprestado novoObj, ItemEmprestado obj);

    /**
     * Atualiza um objeto do tipo {@link ItemEmprestado}
     *
     * @param obj objeto de {@link ItemEmprestado} que possui dados a ser atualizado
     * @return objeto {@link ItemEmprestado} com os dados atualizados
     */
    ItemEmprestado atualizarItemEmprestado(ItemEmprestado obj);

    /**
     * Lista todos os objetos atrasados de {@link ItemEmprestado}
     *
     * @return lista de {@link ItemEmprestado}
     */
    List<ItemEmprestado> listarItensEmAtraso();

    /**
     * Lista todos os objetos disponiveis de {@link ItemEmprestado}
     *
     * @return lista de {@link ItemEmprestado}
     */
    List<ItemEmprestado> listarItensDisponiveis();

    /**
     * Lista todos os objetos {@link ItemEmprestadoDTO}
     *
     * @param atrasado    parâmetro booleano se verdadeiro retorna lista de atrasados
     * @param disponiveis parâmetro booleano se verdadeiro retorna lista de disponiveis
     * @return lista de {@link ItemEmprestadoDTO}
     */
    List<ItemEmprestadoDTO> listarItensDTO(boolean atrasado, boolean disponiveis);
}