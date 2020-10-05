package br.com.ntendencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
