package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.dto.MutuanteDTO;

import java.util.List;

public interface MutuanteService {

	void deleteMutuante(String id);
	MutuanteDTO procurarPorId(String id);
	List<Mutuante> listaMutuantes();
	List<MutuanteDTO> listaMutuantesDTO();
	Integer gerarId ();
	Mutuante salvarMutuante(MutuanteDTO mutuanteDTO);
	MutuanteDTO procurarPorNome(String nome);
}
