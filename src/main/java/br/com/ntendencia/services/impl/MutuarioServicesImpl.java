package br.com.ntendencia.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;

@Service
public class MutuarioServicesImpl implements MutuarioService {

	@Autowired
	private MutuarioRepository mutuarioRepo;
	
	@Override
	public void mutuarioSave(Mutuario mutuario) {
		mutuarioRepo.save(mutuario);
		
	}

	@Override
	public void deleteMutuario(String id) {
		mutuarioRepo.deleteById(id);
		
	}

	@Override
	public List<ItemEmprestado> listaItens(String id) {
		Mutuario obj = mutuarioRepo.findById(id).get();
		
		return obj.getItemsEmprestados();
	}
	
}
