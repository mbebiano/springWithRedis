package br.com.ntendencia.services;

import br.com.ntendencia.domain.ContratoEmprestimo;

import java.util.List;

public interface ContratoEmprestimoService {
	
	ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo);
	void deleteContratoEmprestimo(String id);
	
	List<ContratoEmprestimo> listarTodoscontratosEmprestimo();
	
}
