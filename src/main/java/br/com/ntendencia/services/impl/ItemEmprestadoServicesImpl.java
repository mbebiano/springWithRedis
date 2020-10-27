package br.com.ntendencia.services.impl;

import java.util.List;
import java.util.Optional;

import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.services.ItemEmprestadoService;

@Service
public class ItemEmprestadoServicesImpl implements ItemEmprestadoService {

	@Autowired
	private ItemEmprestadoRepository itemEmprestadoRepo;
	@Autowired
	private MutuanteServicesImpl mutuanteService;
	
	@Override
	public ItemEmprestado itemEmprestadoSave(ItemEmprestado itemEmprestado) {
		String id = itemEmprestado.getMutuanteDTO().getId();
		try{
			if (mutuanteService.procurarPorId(id) == null) {
				throw new ResourceNotFoundException(id);
			}
			if (itemEmprestado.getId()!= null){
				throw new ResourceNotFoundException(itemEmprestado.getId());
			}
			return itemEmprestadoRepo.save(itemEmprestado);
		}
		catch (RuntimeException e){
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	@Override
	public void deleteItemEmprestado(String id) {
		itemEmprestadoRepo.deleteById(id);
		
	}
	@Override
	public List<ItemEmprestado> itensEmprestados() {
		return (List<ItemEmprestado>) itemEmprestadoRepo.findAll();
	}

	@Override
	public Optional<ItemEmprestado> procuraItemEmprestado(String id) {
		Optional<ItemEmprestado> obj = itemEmprestadoRepo.findById(id);
		return obj;
	}

}
