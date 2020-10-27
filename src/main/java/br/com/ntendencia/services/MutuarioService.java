package br.com.ntendencia.services;

import java.util.List;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.ItemEmprestadoDTO;

public interface MutuarioService {
	
	void mutuarioSave(Mutuario mutuario);
	void deleteMutuario(String id);
	//List<ItemEmprestado> listaItens(String id);
	
	Mutuario findById(String id);
	
	
	List<Mutuario> listaMutuarios();

	Integer gerarId ();

	Mutuario salvarMutuario(Mutuario mutuario);

	Mutuario procurarPorNome(String nome);

	void atualizaMutuarioItens(String id, ItemEmprestadoDTO itemEmprestadoDTO);

}
