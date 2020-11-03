package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
		System.out.println(mutuarioRepo.findById(id).isPresent());
		if(mutuarioRepo.findById(id).isPresent()){
			mutuarioRepo.deleteById(id);
		}
		else{
			throw new ResourceNotFoundException("Mutuario id: "+id+" não foi encontrado.");
		}
	}

	@Override
	public Mutuario findById(String id) {
		if(mutuarioRepo.findById(id).isPresent()){
			return mutuarioRepo.findById(id).get();
		}
		else{
			throw new ResourceNotFoundException("Mutuario id: "+id+" não foi encontrado.");
		}
	}
	
	@Override
	public List<Mutuario> listaMutuarios() {
		if (mutuarioRepo.findAll() != null) {
			return (List<Mutuario>) mutuarioRepo.findAll();
		}
		else {
			throw new ResourceNotFoundException("Não há mutuarios registrados");
		}
	}

	@Override
	public Integer gerarId() {
		List<Mutuario> mutuarios = listaMutuarios();
		Integer ultimoIdMutuario = 0;
		Optional<Mutuario> mutuarioOpt = mutuarios.stream().max(Comparator.comparingInt(Mutuario::getIdMutuario));
		if (mutuarioOpt.isPresent()){
			ultimoIdMutuario = mutuarioOpt.get().getIdMutuario();
		}
		return ultimoIdMutuario+1;
	}

	@Override
	public Mutuario salvarMutuario(Mutuario mutuario) {
		Integer idMutuario = gerarId();
		mutuario.setIdUsuario(idMutuario.toString());
		mutuario.setIdMutuario(idMutuario);
		return mutuarioRepo.save(mutuario);
	}

	@Override
	public Mutuario procurarPorNome(String nome) {
		if(mutuarioRepo.findByName(nome).isPresent()){
			return mutuarioRepo.findByName(nome).orElseThrow();
		}
		else{
			throw new ResourceNotFoundException("Mutuario com nome: "+nome+" não encontrado");
		}
	}

}
