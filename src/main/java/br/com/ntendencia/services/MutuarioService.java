package br.com.ntendencia.services;

import br.com.ntendencia.domain.Mutuario;

public interface MutuarioService {
	
	void mutuarioSave(Mutuario mutuario);
	void deleteMutuario(String id);
}
