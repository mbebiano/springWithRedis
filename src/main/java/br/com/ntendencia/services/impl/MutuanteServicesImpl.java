package br.com.ntendencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.services.MutuanteService;

@Service
public class MutuanteServicesImpl implements MutuanteService {

	@Autowired
	private MutuanteRepository mutuanteRepo;
	
	@Override
	public void mutuanteSave(Mutuante mutuante) {
		mutuanteRepo.save(mutuante);
	}
	
	@Override
	public void deleteMutuante(@PathVariable String id) {
		 mutuanteRepo.deleteById(id);
	}
	
}
