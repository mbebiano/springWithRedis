package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;

import java.util.List;

public interface MutuarioService {

    /**
     * Procura e deleta, se existir, um objeto {@link Mutuario}
     * @param id Id de um objeto {@link Mutuario}
     */
    void deleteMutuario(String id);

    /**
     * Procura e retorna, se existir, um objeto {@link MutuarioDTO}
     * @param id Id de um objeto {@link MutuarioDTO}
     * @return objeto de {@link MutuarioDTO}
     */
    MutuarioDTO findById(String id);

    /**
     * Lista todos os objetos {@link Mutuario}
     *
     * @return lista de {@link Mutuario}
     */
    List<Mutuario> listaMutuarios();

    /**
     * Lista todos os objetos {@link MutuarioDTO}
     *
     * @return lista de {@link MutuarioDTO}
     */
    List<MutuarioDTO> listaMutuariosDTO();

    /**
     * Gera um id de forma incremental com base no ultimo objeto salvo
     * @return id gerado de forma incremental
     */
    Integer gerarId();

    /**
     * Salva um objeto {@link Mutuario}
     *
     * @param mutuarioDTO DTO do objeto a ser salvo
     * @return um objeto salvo {@link Mutuario}
     */
    Mutuario salvarMutuario(MutuarioDTO mutuarioDTO);

    /**
     * Procura por nome e retorna o objeto {@link MutuarioDTO}, caso encontrado
     *
     * @param nome nome a ser buscado
     * @return {@link MutuarioDTO} caso encontrado
     */
    MutuarioDTO procurarPorNome(String nome);
}