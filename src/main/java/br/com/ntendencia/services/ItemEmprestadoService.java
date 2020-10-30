package br.com.ntendencia.services;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.enums.EStatus;

import java.util.List;
import java.util.Optional;

public interface ItemEmprestadoService {
	
	ItemEmprestado salvarItemEmprestado(ItemEmprestado itemEmprestado);
	void deletarItemEmprestado(String id);
	List<ItemEmprestado> listarItensEmprestados();
	Optional<ItemEmprestado> procurarItemEmprestado(String id);
	void alterarStatusItemEmprestado(String id, EStatus status);
	Integer gerarId ();
	void definirDataDeEmprestimo(ItemEmprestado itemEmprestado);
	public void atualizarDadoItemEmprestado(Optional<ItemEmprestado> novoObj, ItemEmprestado obj);
	public ItemEmprestado atualizarItemEmprestado(ItemEmprestado obj);
}
