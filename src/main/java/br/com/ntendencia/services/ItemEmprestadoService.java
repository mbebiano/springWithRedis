package br.com.ntendencia.services;

import java.util.List;
import java.util.Optional;

import br.com.ntendencia.domain.ItemEmprestado;

public interface ItemEmprestadoService {
	
	ItemEmprestado itemEmprestadoSave(ItemEmprestado itemEmprestado);
	void deleteItemEmprestado(String id);
	List<ItemEmprestado> itensEmprestados();

	Optional<ItemEmprestado> procuraItemEmprestado(String id);
}
