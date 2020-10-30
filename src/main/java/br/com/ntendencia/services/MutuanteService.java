package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuante;

import java.util.List;

public interface MutuanteService {
	
	void mutuanteSave(Mutuante mutuante);
	void deleteMutuante(String id);
	Mutuante procurarPorId(String id);
	List<Mutuante> listaMutuantes();
	Integer gerarId ();
	Mutuante salvarMutuante(Mutuante mutuante);
	Mutuante procurarPorNome(String nome);
}
