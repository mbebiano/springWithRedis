package br.com.ntendencia.services;

import java.util.List;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuario;

public interface MutuarioService {
	
	void mutuarioSave(Mutuario mutuario);
	void deleteMutuario(String id);
	List<ItemEmprestado> listaItens(String id);
	
	Mutuario findById(String id);
	
	
	List<Mutuario> listaMutuarios();

	Integer gerarId ();

	Mutuario salvarMutuario(Mutuario mutuario);

}
