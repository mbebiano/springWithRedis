package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuario;
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
		return mutuanteOBJ.get();
	}

	@Override
	public List<Mutuante> listaMutuantes() {
		List<Mutuante> list =(List<Mutuante>) mutuanteRepo.findAll();
		return list;
	}

	@Override
	public Integer gerarId() {
		List<Mutuante> mutuante = listaMutuantes();
		Integer ultimoReferencial = 0;
		Optional<Mutuante> mutuanteOpt = mutuante.stream().max(Comparator.comparingInt(Mutuante::getIdReferencial));
		if (mutuanteOpt.isPresent()){
			ultimoReferencial = mutuanteOpt.get().getIdReferencial();
		}

		return ultimoReferencial+1;
	}

	@Override
	public Mutuante salvarMutuario(Mutuante mutuante) {
		Integer idReferencial = gerarId();
		mutuante.setId(idReferencial.toString());
		mutuante.setIdReferencial(idReferencial);

		return mutuanteRepo.save(mutuante);
	}

	@Override
	public Mutuante procurarPorNome(String nome) {
		Optional<Mutuante> mutuanteOpt = mutuanteRepo.findByName(nome);
		Mutuante mutuante = mutuanteOpt.get();
		return mutuante;
	}

}
