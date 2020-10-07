package br.com.ntendencia.services;

import java.util.List;

import br.com.ntendencia.domain.ContratoEmprestimo;

public interface ContratoEmprestimoService {
	
	void contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo);
	void deleteContratoEmprestimo(String id);
	
	List<ContratoEmprestimo> contratosEmprestimo();
}