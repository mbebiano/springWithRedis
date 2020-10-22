package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuante;

public interface MutuanteService {
	
	void mutuanteSave(Mutuante mutuante);
	void deleteMutuante(String id);
	Mutuante procurarPorId(String id);
}
