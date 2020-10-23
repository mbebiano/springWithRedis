package br.com.ntendencia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;

@Service
public class ContratoEmprestimoServicesImpl implements ContratoEmprestimoService {

	@Autowired
	private ContratoEmprestimoRepository contratoEmprestimoRepo;
	
	
	@Override
	public void contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo) {
		contratoEmprestimoRepo.save(contratoEmprestimo);
	}

	@Override
	public void deleteContratoEmprestimo(String id) {
		contratoEmprestimoRepo.deleteById(id);
		
	}

	@Override
	public List<ContratoEmprestimo> contratosEmprestimo() {
		return (List<ContratoEmprestimo>) contratoEmprestimoRepo.findAll();
	}
}
