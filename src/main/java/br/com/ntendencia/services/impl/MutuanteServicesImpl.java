package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.services.MutuanteService;

import java.util.Optional;

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

	@Override
	public Mutuante procurarPorId(String id) {
		Optional<Mutuante> mutuanteOBJ = mutuanteRepo.findById(id);
		return mutuanteOBJ.get();
	}

}
