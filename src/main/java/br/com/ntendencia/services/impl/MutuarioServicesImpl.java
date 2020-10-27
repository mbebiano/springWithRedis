package br.com.ntendencia.services.impl;

import java.util.Comparator;
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

//	@Override
//	public List<ItemEmprestado> listaItens(String id) {
//
//
//		Mutuario obj = mutuarioRepo.findById(id).get();
//
//		return obj.getItemsEmprestados();
//	}

	@Override
	public Mutuario findById(String id) {
		
		Optional<Mutuario> mutuarioOBJ = mutuarioRepo.findById(id);
		return mutuarioOBJ.get();
	}
	
	@Override
	public List<Mutuario> listaMutuarios() {
		List<Mutuario> list =(List<Mutuario>) mutuarioRepo.findAll();

		return list;
	}

	@Override
	public Integer gerarId() {
		List<Mutuario> mutuarios = listaMutuarios();
		Integer ultimoReferencial = 0;
		Optional<Mutuario> mutuarioOpt = mutuarios.stream().max(Comparator.comparingInt(Mutuario::getIdReferencial));
		if (mutuarioOpt.isPresent()){
			ultimoReferencial = mutuarioOpt.get().getIdReferencial();
		}

		return ultimoReferencial+1;
	}

	@Override
	public Mutuario salvarMutuario(Mutuario mutuario) {
		Integer idReferencial = gerarId();
		mutuario.setId(idReferencial.toString());
		mutuario.setIdReferencial(idReferencial);

		return mutuarioRepo.save(mutuario);
	}

	@Override
	public Mutuario procurarPorNome(String nome) {
		Optional<Mutuario> mutuarioOpt = mutuarioRepo.findByName(nome);
		Mutuario mutuario = mutuarioOpt.get();
		return mutuario;
	}

	@Override
	public void atualizaMutuario(Mutuario mutuario) {
		Mutuario mutuarioToUpdate = findById(mutuario.getId());
	}
}
