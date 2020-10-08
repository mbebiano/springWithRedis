package br.com.ntendencia.services;

import java.util.List;

import br.com.ntendencia.domain.ItemEmprestado;

public interface ItemEmprestadoService {
	
	void itemEmprestadoSave(ItemEmprestado itemEmprestado);
	void deleteItemEmprestado(String id);
	List<ItemEmprestado> itensEmprestados();
}
