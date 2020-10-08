package br.com.ntendencia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.services.ItemEmprestadoService;

@Service
public class ItemEmprestadoServicesImpl implements ItemEmprestadoService {

	@Autowired
	private ItemEmprestadoRepository itemEmprestadoRepo;
	
	@Override
	public void itemEmprestadoSave(ItemEmprestado itemEmprestado) {
		itemEmprestadoRepo.save(itemEmprestado);
		
	}
	@Override
	public void deleteItemEmprestado(String id) {
		itemEmprestadoRepo.deleteById(id);
		
	}
	@Override
	public List<ItemEmprestado> itensEmprestados() {
		
		return (List<ItemEmprestado>) itemEmprestadoRepo.findAll();
	}
	
}
