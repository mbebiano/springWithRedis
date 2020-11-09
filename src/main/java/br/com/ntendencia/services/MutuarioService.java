package br.com.ntendencia.services;

import java.util.List;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;

public interface MutuarioService {

    void deleteMutuario(String id);

    MutuarioDTO findById(String id);

    List<Mutuario> listaMutuarios();

    List<MutuarioDTO> listaMutuariosDTO();

    Integer gerarId();

    Mutuario salvarMutuario(MutuarioDTO mutuarioDTO);

    MutuarioDTO procurarPorNome(String nome);

}
