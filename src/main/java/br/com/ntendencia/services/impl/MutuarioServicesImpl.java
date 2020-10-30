package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;
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
		return mutuarioOBJ.orElseThrow();
	}
	
	@Override
	public List<Mutuario> listaMutuarios() {
		return (List<Mutuario>) mutuarioRepo.findAll();
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
		Optional<Mutuario> mutuarioOpt = mutuarioRepo.findByName(nome);
		Mutuario mutuario = mutuarioOpt.orElseThrow();
		return mutuario;
	}

	@Override
	public void atualizaMutuarioItens(String id, ItemEmprestadoDTO itemEmprestadoDTO) {
		Optional<Mutuario> mutuarioObj = mutuarioRepo.findById(id);
//		if(mutuarioObj.isPresent()){
//			mutuarioObj.get().getItemsEmprestadosDTO().add(itemEmprestadoDTO);
//			mutuarioRepo.save(mutuarioObj.get());
//		}
//		else{
//			throw new ResourceNotFoundException(id);
//		}
	}
}
