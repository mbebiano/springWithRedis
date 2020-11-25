package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.dto.MutuanteDTO;

import java.util.List;

public interface MutuanteService {

    /**
     * Procura e deleta, se existir, um objeto {@link Mutuante}
     * @param id Id de um objeto {@link Mutuante}
     */
    void deleteMutuante(String id);

    /**
     * Procura e retorna, se existir, um objeto {@link MutuanteDTO}
     * @param id Id de um objeto {@link MutuanteDTO}
     * @return objeto de {@link MutuanteDTO}
     */
    MutuanteDTO procurarPorId(String id);

    /**
     * Lista todos os objetos {@link Mutuante}
     *
     * @return lista de {@link Mutuante}
     */
    List<Mutuante> listaMutuantes();

    /**
     * Lista todos os objetos {@link MutuanteDTO}
     *
     * @return lista de {@link MutuanteDTO}
     */
    List<MutuanteDTO> listaMutuantesDTO();

    /**
     * Gera um id de forma incremental com base no ultimo objeto salvo
     * @return id gerado de forma incremental
     */
    Integer gerarId();

    /**
     * Salva um objeto {@link Mutuante}
     *
     * @param mutuanteDTO DTO do objeto a ser salvo
     * @return um objeto salvo {@link Mutuante}
     */
    Mutuante salvarMutuante(MutuanteDTO mutuanteDTO);

    /**
     * Procura por nome e retorna o objeto {@link MutuanteDTO}, caso encontrado
     *
     * @param nome nome a ser buscado
     * @return {@link MutuanteDTO} caso encontrado
     */
    MutuanteDTO procurarPorNome(String nome);
}