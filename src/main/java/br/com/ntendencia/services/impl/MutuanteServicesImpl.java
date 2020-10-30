package br.com.ntendencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.services.MutuanteService;

import java.util.Comparator;
import java.util.List;
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
		return mutuanteOBJ.orElse(null);
	}

	@Override
	public List<Mutuante> listaMutuantes() {
		return (List<Mutuante>) mutuanteRepo.findAll();
	}

	@Override
	public Integer gerarId() {
		List<Mutuante> mutuante = listaMutuantes();
		Integer ultimoIdMutuante = 0;
		Optional<Mutuante> mutuanteOpt = mutuante.stream().max(Comparator.comparingInt(Mutuante::getIdMutuante));
		if (mutuanteOpt.isPresent()){
			ultimoIdMutuante = mutuanteOpt.get().getIdMutuante();
		}
		return ultimoIdMutuante+1;
	}

	@Override
	public Mutuante salvarMutuante(Mutuante mutuante) {
		Integer idMutuante = gerarId();
		mutuante.setIdUsuario(idMutuante.toString());
		mutuante.setIdMutuante(idMutuante);
		return mutuanteRepo.save(mutuante);
	}

	@Override
	public Mutuante procurarPorNome(String nome) {
		Optional<Mutuante> mutuanteOpt = mutuanteRepo.findByName(nome);
		return mutuanteOpt.orElse(null);
	}

}
