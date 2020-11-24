package br.com.ntendencia.services;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.enums.EStatus;

import java.util.List;

public interface ItemEmprestadoService {
	
	ItemEmprestado salvarItemEmprestado(ItemEmprestadoDTO itemEmprestadoDTO);
	void deletarItemEmprestado(String id);
	List<ItemEmprestado> listarItensEmprestados();
	ItemEmprestado procurarItemEmprestado(String id);
	ItemEmprestadoDTO procurarItemEmprestadoDTO(String id);
	void alterarStatusItemEmprestado(String id, EStatus status);
	Integer gerarId ();
	void definirDataDeEmprestimo(ItemEmprestado itemEmprestado);
	void atualizarDadoItemEmprestado(ItemEmprestado novoObj, ItemEmprestado obj);
	ItemEmprestado atualizarItemEmprestado(ItemEmprestado obj);
	List<ItemEmprestado> listarItensEmAtraso();
	List<ItemEmprestado> listarItensDisponiveis();
	List<ItemEmprestadoDTO> listarItensDTO(boolean atrasado, boolean disponiveis);
}
